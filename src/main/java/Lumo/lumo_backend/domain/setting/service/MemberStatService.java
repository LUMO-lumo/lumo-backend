package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.MemberStatResDTO;
import Lumo.lumo_backend.domain.setting.entity.MemberStat;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.domain.setting.repository.MemberStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static Lumo.lumo_backend.domain.member.status.MemberErrorCode.CANT_FOUND_MEMBER;
import static Lumo.lumo_backend.domain.setting.status.SettingErrorCode.SETTING_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberStatService {

    private final MemberRepository memberRepository;



    @Transactional(readOnly = true)
    public MemberStatResDTO get(Long memberId) {

        // member, memberStat 획득
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(CANT_FOUND_MEMBER));
        MemberStat memberStat = member.getStat();

        // 유효성 검사
        if (memberStat == null) {
            throw new SettingException(SETTING_NOT_FOUND);
        }

        return MemberStatResDTO.from(memberStat);
    }
}
