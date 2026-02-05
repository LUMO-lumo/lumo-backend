package Lumo.lumo_backend.domain.member.service;

import Lumo.lumo_backend.domain.alarm.entity.MissionHistory;
import Lumo.lumo_backend.domain.alarm.entity.repository.MissionHistoryRepository;
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
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableAsync
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final RedisTemplate redisTemplate;
    private final JavaMailSender mailSender;
    private final JWTProvider jwtProvider;
    private final BCryptPasswordEncoder encoder;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 4;

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
            throw new MemberException(MemberErrorCode.EXIST_MEMBER);
        } else {
            return true;
        }
    }

    @Async
    public void requestVerificationCode(String email) {

        if (redisTemplate.opsForValue().get(email) != null){
            throw new MemberException(MemberErrorCode.ALREADY_SEND); // 따닥 방지
        }

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper;
        String code;

        try {
            code = generateVerificationCode();
            helper = new MimeMessageHelper(msg, true, "utf-8");
            helper.setTo(email);
            helper.setFrom("no-reply@mail.com", "no-reply@mail.com");
            helper.setSubject("Lumo 인증 이메일 알림.");
            helper.setText("<!DOCTYPE html>\n" +
                    "<html lang=\"ko\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <style>\n" +
                    "        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap');\n" +
                    "        \n" +
                    "        body {\n" +
                    "            font-family: 'Noto Sans KR', 'Malgun Gothic', 'Apple SD Gothic Neo', Arial, sans-serif;\n" +
                    "            background-color: #f4f6f9;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "        .container {\n" +
                    "            width: 100%;\n" +
                    "            max-width: 580px;\n" +
                    "            margin: 40px auto;\n" +
                    "            background-color: #ffffff;\n" +
                    "            border-radius: 12px;\n" +
                    "            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);\n" +
                    "            overflow: hidden;\n" +
                    "            border: 1px solid #e9ecef;\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            background-color: #ffffff;\n" +
                    "            padding: 30px 20px 20px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .header h2 {\n" +
                    "            font-size: 24px;\n" +
                    "            color: #212529;\n" +
                    "            font-weight: 700;\n" +
                    "            margin: 0;\n" +
                    "        }\n" +
                    "        .content {\n" +
                    "            padding: 20px;\n" +
                    "            text-align: center;\n" +
                    "            line-height: 1.6;\n" +
                    "            color: #495057;\n" +
                    "        }\n" +
                    "        .verification-code-container {\n" +
                    "            margin: 30px 0;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .verification-code {\n" +
                    "            display: inline-block;\n" +
                    "            background-color: #eaf3ff;\n" +
                    "            padding: 15px 30px;\n" +
                    "            font-size: 28px;\n" +
                    "            font-weight: bold;\n" +
                    "            letter-spacing: 3px;\n" +
                    "            border-radius: 8px;\n" +
                    "            color: #0056b3;\n" +
                    "            border: 1px dashed #ced4da;\n" +
                    "            -webkit-user-select: all;\n" +
                    "            -moz-user-select: all;\n" +
                    "            -ms-user-select: all;\n" +
                    "            user-select: all;\n" +
                    "        }\n" +
                    "        .instruction {\n" +
                    "            margin-top: 10px;\n" +
                    "            font-size: 14px;\n" +
                    "            color: #6c757d;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px;\n" +
                    "            border-top: 1px solid #e9ecef;\n" +
                    "            font-size: 12px;\n" +
                    "            color: #adb5bd;\n" +
                    "            background-color: #f8f9fa;\n" +
                    "        }\n" +
                    "        .logo {\n" +
                    "            width: 50px;\n" +
                    "            height: 50px;\n" +
                    "            background-color: #007bff;\n" +
                    "            border-radius: 50%;\n" +
                    "            margin: 0 auto 15px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"container\">\n" +
                    "    <div class=\"header\">\n" +
                    "        \n" +
                    "        <div class=\"logo\"></div>\n" +
                    "        <h2>Lumo 이메일 인증</h2>\n" +
                    "    </div>\n" +
                    "    <div class=\"content\">\n" +
                    "        <p>안녕하세요, Lumo입니다. 이메일 주소 인증을 위해 아래 코드를 사용해 주세요.</p>\n" +
                    "        <div class=\"verification-code-container\">\n" +
                    "            <span class=\"verification-code\">  " + code + "  </span>\n" +
                    "        </div>\n" +
                    "        <p>이 코드는 3분 동안 유효합니다.</p>\n" +
                    "        <p class=\"instruction\">코드를 복사하여 앱에 붙여넣어 주세요.</p>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <p>이 메일은 발신 전용입니다. 문의사항은 관리자 이메일로 문의 부탁 드립니다.</p>\n" +
                    "        <p>&copy; 2026 Lumo. All Rights Reserved.</p>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>", true);
            helper.setReplyTo("no-reply@mail.com");
            mailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MemberException(MemberErrorCode.CANT_SEND_EMAIL);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        redisTemplate.opsForValue().set(email, code, 180, TimeUnit.SECONDS);

        log.info("[MemberService] saved code {} to {}", redisTemplate.opsForValue().get(email), email);
    }

    public static String generateVerificationCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());

            code.append(CHARACTERS.charAt(randomIndex));
        }

        return code.toString();
    }


    public void verifyCode(String email, String code) {
        String savedCode = (String) redisTemplate.opsForValue().get(email);
        log.info("saved code {} to {}", savedCode, email);

        if (savedCode == null) {
            throw new MemberException(MemberErrorCode.WRONG_CODE);
        } else if (!savedCode.equals(code)) {
            throw new MemberException(MemberErrorCode.WRONG_CODE);
        }
    }

    public void signIn(MemberReqDTO.SignInRequestDTO dto) {
        Optional<Member> byEmail = memberRepository.findByEmail(dto.getEmail());

        if (byEmail.isPresent()) {
            throw new MemberException(MemberErrorCode.EXIST_MEMBER);
        }

        memberRepository.save(Member.create(dto.getEmail(), dto.getUsername(), encoder.encode(dto.getPassword()), Login.NORMAL, MemberRole.USER));
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

        return jwt;
    }

    public void logout (Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
        redisTemplate.delete("refresh:"+member.getEmail());
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
