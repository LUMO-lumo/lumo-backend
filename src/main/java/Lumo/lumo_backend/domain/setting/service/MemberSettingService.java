package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingResDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingUpdateReqDTO;
import Lumo.lumo_backend.domain.setting.entity.memberSetting.MemberSetting;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static Lumo.lumo_backend.domain.member.status.MemberErrorCode.CANT_FOUND_MEMBER;
import static Lumo.lumo_backend.domain.setting.status.SettingErrorCode.SETTING_NOT_FOUND;
import static Lumo.lumo_backend.global.apiResponse.status.ErrorCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class MemberSettingService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberSettingResDTO get(Long memberId) {

        // member, memberSetting 획득
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(CANT_FOUND_MEMBER));
        MemberSetting memberSetting = member.getSetting();

        // 유효성 검사
        if (memberSetting == null) {
            throw new SettingException(SETTING_NOT_FOUND);
        }

        return MemberSettingResDTO.from(memberSetting);
    }

    @Transactional
    public void update(Long memberId, MemberSettingUpdateReqDTO request) {

        // member, memberSetting 획득
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(CANT_FOUND_MEMBER));
        MemberSetting memberSetting = member.getSetting();

        // 유효성 검사
        if (memberSetting == null) {
            throw new SettingException(SETTING_NOT_FOUND);
        }


        boolean smartBreifing = request.isSmartBriefing();
        if (smartBreifing && !(member.getIsProUpgraded())) {
            smartBreifing = false;
        }



        // memberSetting 업데이트
        try {
            memberSetting.update(
                    request.getTheme(),
                    request.getLanguage(),
                    request.isBatterySaving(),
                    request.getAlarmOffMissionDefaultType(),
                    request.getAlarmOffMissionDefaultLevel(),
                    request.getAlarmOffMissionDefaultDuration(),
                    request.getBriefingSentence(),
                    request.getBriefingVoiceDefaultType(),
                    smartBreifing
            );
        } catch (Exception e) {
            throw new GeneralException(INTERNAL_SERVER_ERROR);
        }

    }
}

