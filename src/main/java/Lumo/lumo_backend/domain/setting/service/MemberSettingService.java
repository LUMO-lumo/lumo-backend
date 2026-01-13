package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingResponseDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingUpdateRequestDTO;
import Lumo.lumo_backend.domain.setting.entity.memberSetting.MemberSetting;
import Lumo.lumo_backend.domain.setting.repository.MemberSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberSettingService {

    private final MemberSettingRepository memberSettingRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberSettingResponseDTO get(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MemberSetting memberSetting = member.getSetting();

        return MemberSettingResponseDTO.from(memberSetting);
    }

    public void update(Long memberId, MemberSettingUpdateRequestDTO request) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        MemberSetting memberSetting = member.getSetting();
        memberSetting.update(
                request.getTheme(),
                request.getLanguage(),
                request.isBatterySaving(),
                request.getAlarmOffMissionDefaultType(),
                request.getAlarmOffMissionDefaultLevel(),
                request.getAlarmOffMissionDefaultDuration(),
                request.getBriefingSentence(),
                request.getBriefingVoiceDefaultType()
        );
    }
}

