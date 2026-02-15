package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingResDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingUpdateReqDTO;
import Lumo.lumo_backend.domain.setting.entity.memberSetting.MemberSetting;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.domain.setting.repository.MemberSettingRepository;
import Lumo.lumo_backend.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static Lumo.lumo_backend.domain.member.status.MemberErrorCode.CANT_FOUND_MEMBER;
import static Lumo.lumo_backend.domain.setting.status.SettingErrorCode.SETTING_NOT_FOUND;
import static Lumo.lumo_backend.global.apiResponse.status.ErrorCode.INTERNAL_SERVER_ERROR;

@Service
@RequiredArgsConstructor
public class MemberSettingService {

    private final MemberRepository memberRepository;
    private final RedisTemplate redisTemplate;

    @Transactional(readOnly = true)
    public MemberSettingResDTO get(Long memberId) {

        String key = "user:%d:settings".formatted(memberId);

        Map<Object, Object> cached = redisTemplate.opsForHash().entries(key);
        if (!cached.isEmpty()) {
            return MemberSettingResDTO.fromMap(cached);
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(CANT_FOUND_MEMBER));

        MemberSetting setting = member.getSetting();
        if (setting == null) {
            throw new SettingException(SETTING_NOT_FOUND);
        }

        redisTemplate.opsForHash().putAll(key, toMap(setting));

        return MemberSettingResDTO.from(setting);
    }

    @Transactional
    public void update(Long memberId, MemberSettingUpdateReqDTO request) {

        // memberSetting 획득
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

    }

    private Map<String, String> toMap(MemberSetting s) {
        Map<String, String> map = new HashMap<>();

        map.put("theme", s.getTheme().name());
        map.put("language", s.getLanguage().name());
        map.put("batterySaving", String.valueOf(s.isBatterySaving()));
        map.put("alarmOffMissionDefaultType", s.getAlarmOffMissionDefaultType().name());
        map.put("alarmOffMissionDefaultLevel", s.getAlarmOffMissionDefaultLevel().name());
        map.put("alarmOffMissionDefaultDuration", String.valueOf(s.getAlarmOffMissionDefaultDuration()));
        map.put("briefingSentence", s.getBriefingSentence());
        map.put("briefingVoiceDefaultType", s.getBriefingVoiceDefaultType().name());
        map.put("smartBriefing", String.valueOf(s.isSmartBriefing()));

        return map;
    }


}

