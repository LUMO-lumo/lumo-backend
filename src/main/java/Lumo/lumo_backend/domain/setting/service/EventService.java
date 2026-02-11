package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.setting.entity.MemberStat;
import Lumo.lumo_backend.domain.setting.repository.MemberStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


//@Service
@RequiredArgsConstructor
public class EventService {

    private final MemberStatRepository memberStatRepository;
    private final MemberRepository memberRepository;

    public void alarmActivate(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER)
                );


        MemberStat memberStat = member.getStat();

    }
}
