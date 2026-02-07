package Lumo.lumo_backend.domain.member.service;

import Lumo.lumo_backend.domain.alarm.entity.MissionHistory;
import Lumo.lumo_backend.domain.alarm.entity.repository.MissionHistoryRepository;
import Lumo.lumo_backend.domain.email.service.EmailService;
import Lumo.lumo_backend.domain.member.dto.MemberReqDTO;
import Lumo.lumo_backend.domain.member.dto.MemberRespDTO;
import Lumo.lumo_backend.domain.member.dto.MissionStat;
import Lumo.lumo_backend.domain.member.entity.memberEnum.Login;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.entity.memberEnum.MemberRole;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.global.security.jwt.JWT;
import Lumo.lumo_backend.global.security.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableAsync
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final EmailService emailService;
    private final RedisTemplate redisTemplate;
    private final JWTProvider jwtProvider;
    private final BCryptPasswordEncoder encoder;


    public MemberRespDTO.GetLoginDTO getLogin(Member member) {

        if (member == null) {
            throw new MemberException(MemberErrorCode.CANT_FOUND_MEMBER);
        }

        Optional<Member> byEmail = memberRepository.findByEmail(member.getEmail());
        if (byEmail.isPresent()) {
            return MemberRespDTO.GetLoginDTO.builder().login(byEmail.get().getLogin()).build();
        } else {
            return MemberRespDTO.GetLoginDTO.builder().login(Login.NULL).build();
        }
    }

    @Transactional(readOnly = true)
    public Boolean checkEmailDuplicate(String email) {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            log.info("[MemberService - checkEmailDuplicate] duplicate email {}", email);
            throw new MemberException(MemberErrorCode.EXIST_MEMBER);
        } else {
            log.info("[MemberService - checkEmailDuplicate] Success to check duplicate {}", email);
            return true;
        }
    }

    public void requestVerificationCode(String email) {
        String preCode = (String) redisTemplate.opsForValue().get(email);

        if (preCode != null){
            log.info("[MemberService - requestVerificationCode] already send to {} with {}", email, preCode);
            throw new MemberException(MemberErrorCode.ALREADY_SEND); // 따닥 방지
        }
        else{
            emailService.sendEmail(email);
        }
    }


    public void verifyCode(String email, String code) {
        String savedCode = (String) redisTemplate.opsForValue().get(email);

        if (savedCode == null) {
            throw new MemberException(MemberErrorCode.WRONG_CODE);
        } else if (!savedCode.equals(code)) {
            throw new MemberException(MemberErrorCode.WRONG_CODE);
        }
        log.info("[MemberService - verifyCode] Success to verify code {} to {}", savedCode, email);
    }

    public void signIn(MemberReqDTO.SignInRequestDTO dto) {
        Optional<Member> byEmail = memberRepository.findByEmail(dto.getEmail());

        if (byEmail.isPresent()) {
            throw new MemberException(MemberErrorCode.EXIST_MEMBER);
        }

        memberRepository.save(Member.create(dto.getEmail(), dto.getUsername(), encoder.encode(dto.getPassword()), Login.NORMAL, MemberRole.USER));

        log.info("[MemberService - signIn] Success to signIn -> {}, {}", dto.getEmail(), dto.getUsername());
    }

    public JWT login(MemberReqDTO.LoginReqDTO dto) {
        Member member = memberRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
        if (!encoder.matches(dto.getPassword(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.CANT_FOUND_MEMBER);
        }

        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(member.getRole().toString()));
        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword(), authorities);
        JWT jwt = jwtProvider.generateToken(authentication);

        redisTemplate.opsForValue().set("refresh:"+dto.getEmail(), jwt.getRefreshToken());

        log.info("[MemberService - login] Success to login -> {} - {}", dto.getEmail(), jwt.getRefreshToken());

        return jwt;
    }

    public void logout (Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
        redisTemplate.delete("refresh:"+member.getEmail());
        log.info("[MemberService - logout] Success to logout -> {}", member.getEmail());
    }

    public MemberRespDTO.FindEmailRespDTO findEmail(String email) {
        boolean existsByEmail = memberRepository.existsByEmail(email);

        if (existsByEmail) {
            return MemberRespDTO.FindEmailRespDTO.builder().email(email).build();
        }
        else{
            throw new MemberException(MemberErrorCode.CANT_FOUND_MEMBER);
        }
    }

    public MemberRespDTO.GetMissionRecordRespDTO getMissionRecord (Member persistedMember) {

        MissionStat missionStat = missionHistoryRepository.findMissionStatsByMember(persistedMember.getId(), LocalDate.now().withDayOfMonth(1).atStartOfDay());

        return MemberRespDTO.GetMissionRecordRespDTO.builder()
                .missionSuccessRate((int) (missionStat.getSuccess() / missionStat.getTotal() *100))
                .consecutiveSuccessCnt(persistedMember.getConsecutiveSuccessCnt())
                .build();
    }

    public void getMissionHistory (Long memberId){
        List<MissionHistory> missionHistoryList = missionHistoryRepository.findAllByMemberId(memberId);
        return;
    }

    // 주 마다 사용자 연속 성공 횟수 정리
    @Scheduled(cron = "0 0 0 * * 0")
    public void asdf(){
        memberRepository.findAll().forEach(Member::initConsecutiveSuccessCnt);
    }

}
