package Lumo.lumo_backend.domain.alarm.entity.service;

import Lumo.lumo_backend.domain.alarm.entity.*;
import Lumo.lumo_backend.domain.alarm.entity.dto.*;
import Lumo.lumo_backend.domain.alarm.entity.exception.AlarmException;
import Lumo.lumo_backend.domain.alarm.entity.exception.code.AlarmErrorCode;
import Lumo.lumo_backend.domain.alarm.entity.repository.*;
import Lumo.lumo_backend.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final AlarmRepeatDayRepository repeatDayRepository;
    private final AlarmLogRepository alarmLogRepository;
    private final MissionContentRepository missionContentRepository;
    private final MissionHistoryRepository missionHistoryRepository;
    private final AlarmSoundService alarmSoundService;

    /**
     * 알람 생성
     */
    @Transactional
    public AlarmResponseDto createAlarm(Member member, AlarmCreateRequestDto requestDto) {
        // 사운드 타입 유효성 검증
        String soundType = validateAndGetSoundType(requestDto.getSoundType());

        // 알람 생성
        Alarm alarm = Alarm.builder()
                .member(member)
                .alarmTime(requestDto.getAlarmTime())
                .label(requestDto.getLabel())
                .isEnabled(requestDto.getIsEnabled())
                .soundType(soundType)
                .vibration(requestDto.getVibration())
                .volume(requestDto.getVolume())
                .build();

        // 반복 요일 설정
        if (requestDto.getRepeatDays() != null && !requestDto.getRepeatDays().isEmpty()) {
            List<AlarmRepeatDay> repeatDays = requestDto.getRepeatDays().stream()
                    .map(dayOfWeek -> AlarmRepeatDay.builder()
                            .dayOfWeek(dayOfWeek)
                            .build())
                    .collect(Collectors.toList());
            alarm.getRepeatDays().addAll(repeatDays);
        }

        // 스누즈 설정
        if (requestDto.getSnoozeSetting() != null) {
            AlarmSnooze snooze = AlarmSnooze.builder()
                    .alarm(alarm)
                    .isEnabled(requestDto.getSnoozeSetting().getIsEnabled())
                    .intervalSec(requestDto.getSnoozeSetting().getIntervalSec())
                    .maxCount(requestDto.getSnoozeSetting().getMaxCount())
                    .build();
            alarm = Alarm.builder()
                    .alarmId(alarm.getAlarmId())
                    .member(alarm.getMember())
                    .alarmTime(alarm.getAlarmTime())
                    .label(alarm.getLabel())
                    .isEnabled(alarm.getIsEnabled())
                    .soundType(alarm.getSoundType())
                    .vibration(alarm.getVibration())
                    .volume(alarm.getVolume())
                    .repeatDays(alarm.getRepeatDays())
                    .alarmSnooze(snooze)
                    .build();
        }

        Alarm savedAlarm = alarmRepository.save(alarm);
        return AlarmResponseDto.from(savedAlarm);
    }

    /**
     * 내 알람 목록 조회
     */
    public List<AlarmResponseDto> getMyAlarms(Member member) {
        List<Alarm> alarms = alarmRepository.findAllByMemberWithDetails(member);
        return alarms.stream()
                .map(AlarmResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 알람 상세 조회
     */
    public AlarmResponseDto getAlarmDetail(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        return AlarmResponseDto.from(alarm);
    }

    /**
     * 알람 수정
     */
    @Transactional
    public AlarmResponseDto updateAlarm(Member member, Long alarmId, AlarmUpdateRequestDto requestDto) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        // 사운드 타입 유효성 검증 (수정 요청에 포함된 경우만)
        String soundType = requestDto.getSoundType() != null
                ? validateAndGetSoundType(requestDto.getSoundType())
                : alarm.getSoundType();

        // 빌더 패턴으로 업데이트 (불변 객체 패턴)
        Alarm updatedAlarm = Alarm.builder()
                .alarmId(alarm.getAlarmId())
                .member(alarm.getMember())
                .alarmTime(requestDto.getAlarmTime() != null ? requestDto.getAlarmTime() : alarm.getAlarmTime())
                .label(requestDto.getLabel() != null ? requestDto.getLabel() : alarm.getLabel())
                .isEnabled(alarm.getIsEnabled())
                .soundType(soundType)
                .vibration(requestDto.getVibration() != null ? requestDto.getVibration() : alarm.getVibration())
                .volume(requestDto.getVolume() != null ? requestDto.getVolume() : alarm.getVolume())
                .repeatDays(alarm.getRepeatDays())
                .alarmSnooze(alarm.getAlarmSnooze())
                .alarmMission(alarm.getAlarmMission())
                .build();

        // 반복 요일 업데이트
        if (requestDto.getRepeatDays() != null) {
            repeatDayRepository.deleteByAlarm(alarm);
            List<AlarmRepeatDay> newRepeatDays = requestDto.getRepeatDays().stream()
                    .map(dayOfWeek -> AlarmRepeatDay.builder()
                            .alarm(updatedAlarm)
                            .dayOfWeek(dayOfWeek)
                            .build())
                    .collect(Collectors.toList());
            repeatDayRepository.saveAll(newRepeatDays);
            updatedAlarm.getRepeatDays().clear();
            updatedAlarm.getRepeatDays().addAll(newRepeatDays);
        }

        // 스누즈 설정 업데이트
        if (requestDto.getSnoozeSetting() != null && alarm.getAlarmSnooze() != null) {
            AlarmSnooze updatedSnooze = AlarmSnooze.builder()
                    .snoozeId(alarm.getAlarmSnooze().getSnoozeId())
                    .alarm(updatedAlarm)
                    .isEnabled(requestDto.getSnoozeSetting().getIsEnabled() != null
                            ? requestDto.getSnoozeSetting().getIsEnabled()
                            : alarm.getAlarmSnooze().getIsEnabled())
                    .intervalSec(requestDto.getSnoozeSetting().getIntervalSec() != null
                            ? requestDto.getSnoozeSetting().getIntervalSec()
                            : alarm.getAlarmSnooze().getIntervalSec())
                    .maxCount(requestDto.getSnoozeSetting().getMaxCount() != null
                            ? requestDto.getSnoozeSetting().getMaxCount()
                            : alarm.getAlarmSnooze().getMaxCount())
                    .build();
        }

        Alarm savedAlarm = alarmRepository.save(updatedAlarm);
        return AlarmResponseDto.from(savedAlarm);
    }

    /**
     * 알람 삭제
     */
    @Transactional
    public void deleteAlarm(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        alarmRepository.delete(alarm);
    }

    /**
     * 알람 ON/OFF 토글
     */
    @Transactional
    public AlarmResponseDto toggleAlarm(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        Alarm toggledAlarm = Alarm.builder()
                .alarmId(alarm.getAlarmId())
                .member(alarm.getMember())
                .alarmTime(alarm.getAlarmTime())
                .label(alarm.getLabel())
                .isEnabled(!alarm.getIsEnabled())
                .soundType(alarm.getSoundType())
                .vibration(alarm.getVibration())
                .volume(alarm.getVolume())
                .repeatDays(alarm.getRepeatDays())
                .alarmSnooze(alarm.getAlarmSnooze())
                .alarmMission(alarm.getAlarmMission())
                .build();

        Alarm savedAlarm = alarmRepository.save(toggledAlarm);
        return AlarmResponseDto.from(savedAlarm);
    }

    /**
     * 반복 요일 조회
     */
    public List<String> getRepeatDays(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        return alarm.getRepeatDays().stream()
                .map(repeatDay -> repeatDay.getDayOfWeek().name())
                .collect(Collectors.toList());
    }

    /**
     * 반복 요일 설정
     */
    @Transactional
    public List<String> updateRepeatDays(Member member, Long alarmId, RepeatDaysUpdateRequestDto requestDto) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        // 기존 반복 요일 삭제
        repeatDayRepository.deleteByAlarm(alarm);

        // 새 반복 요일 추가
        List<AlarmRepeatDay> newRepeatDays = requestDto.getRepeatDays().stream()
                .map(dayOfWeek -> AlarmRepeatDay.builder()
                        .alarm(alarm)
                        .dayOfWeek(dayOfWeek)
                        .build())
                .collect(Collectors.toList());

        repeatDayRepository.saveAll(newRepeatDays);

        return newRepeatDays.stream()
                .map(repeatDay -> repeatDay.getDayOfWeek().name())
                .collect(Collectors.toList());
    }

    /**
     * 스누즈 설정 조회
     */
    public AlarmResponseDto.SnoozeSettingResponseDto getSnoozeSettings(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        if (alarm.getAlarmSnooze() == null) {
            throw new AlarmException(AlarmErrorCode.ALARM_NOT_FOUND);
        }

        return AlarmResponseDto.SnoozeSettingResponseDto.builder()
                .snoozeId(alarm.getAlarmSnooze().getSnoozeId())
                .isEnabled(alarm.getAlarmSnooze().getIsEnabled())
                .intervalSec(alarm.getAlarmSnooze().getIntervalSec())
                .maxCount(alarm.getAlarmSnooze().getMaxCount())
                .build();
    }

    /**
     * 스누즈 설정 수정
     */
    @Transactional
    public AlarmResponseDto.SnoozeSettingResponseDto updateSnoozeSettings(
            Member member, Long alarmId, AlarmCreateRequestDto.SnoozeSettingDto requestDto) {

        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        AlarmSnooze snooze = alarm.getAlarmSnooze();
        if (snooze == null) {
            // 스누즈 설정이 없으면 생성
            snooze = AlarmSnooze.builder()
                    .alarm(alarm)
                    .isEnabled(requestDto.getIsEnabled())
                    .intervalSec(requestDto.getIntervalSec())
                    .maxCount(requestDto.getMaxCount())
                    .build();
        } else {
            // 기존 스누즈 설정 업데이트
            snooze = AlarmSnooze.builder()
                    .snoozeId(snooze.getSnoozeId())
                    .alarm(alarm)
                    .isEnabled(requestDto.getIsEnabled() != null ? requestDto.getIsEnabled() : snooze.getIsEnabled())
                    .intervalSec(requestDto.getIntervalSec() != null ? requestDto.getIntervalSec() : snooze.getIntervalSec())
                    .maxCount(requestDto.getMaxCount() != null ? requestDto.getMaxCount() : snooze.getMaxCount())
                    .build();
        }

        return AlarmResponseDto.SnoozeSettingResponseDto.builder()
                .snoozeId(snooze.getSnoozeId())
                .isEnabled(snooze.getIsEnabled())
                .intervalSec(snooze.getIntervalSec())
                .maxCount(snooze.getMaxCount())
                .build();
    }

    /**
     * 스누즈 ON/OFF 토글
     */
    @Transactional
    public AlarmResponseDto.SnoozeSettingResponseDto toggleSnooze(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        AlarmSnooze snooze = alarm.getAlarmSnooze();
        if (snooze == null) {
            throw new AlarmException(AlarmErrorCode.ALARM_NOT_FOUND);
        }

        AlarmSnooze toggledSnooze = AlarmSnooze.builder()
                .snoozeId(snooze.getSnoozeId())
                .alarm(alarm)
                .isEnabled(!snooze.getIsEnabled())
                .intervalSec(snooze.getIntervalSec())
                .maxCount(snooze.getMaxCount())
                .build();

        return AlarmResponseDto.SnoozeSettingResponseDto.builder()
                .snoozeId(toggledSnooze.getSnoozeId())
                .isEnabled(toggledSnooze.getIsEnabled())
                .intervalSec(toggledSnooze.getIntervalSec())
                .maxCount(toggledSnooze.getMaxCount())
                .build();
    }

    /**
     * 미션 설정 조회
     */
    public MissionSettingDto getMissionSettings(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        return MissionSettingDto.from(alarm.getAlarmMission());
    }

    /**
     * 미션 설정 수정
     */
    @Transactional
    public MissionSettingDto updateMissionSettings(Member member, Long alarmId, MissionSettingDto requestDto) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        AlarmMission mission = alarm.getAlarmMission();
        if (mission == null) {
            // 미션 설정이 없으면 생성
            mission = AlarmMission.builder()
                    .alarm(alarm)
                    .missionType(requestDto.getMissionType())
                    .difficulty(requestDto.getDifficulty())
                    .walkGoalMeter(requestDto.getWalkGoalMeter())
                    .questionCount(requestDto.getQuestionCount())
                    .build();
        } else {
            // 기존 미션 설정 업데이트
            mission = AlarmMission.builder()
                    .missionId(mission.getMissionId())
                    .alarm(alarm)
                    .missionType(requestDto.getMissionType())
                    .difficulty(requestDto.getDifficulty())
                    .walkGoalMeter(requestDto.getWalkGoalMeter())
                    .questionCount(requestDto.getQuestionCount())
                    .build();
        }

        return MissionSettingDto.from(mission);
    }

    /**
     * 미션 시작 (문제 발급)
     */
    public List<MissionContentResponseDto> startMission(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        AlarmMission mission = alarm.getAlarmMission();

        if (mission == null || mission.getMissionType() == MissionType.NONE) {
            throw new AlarmException(AlarmErrorCode.MISSION_NOT_FOUND);
        }

        // 걷기 미션은 문제가 없음
        if (mission.getMissionType() == MissionType.WALK) {
            return new ArrayList<>();
        }

        // DB에서 랜덤 문제 조회 (이미 구현되어 있음)
        List<MissionContent> contents = missionContentRepository.findRandomByTypeAndDifficulty(
                mission.getMissionType().name(),
                mission.getDifficulty().name(),
                mission.getQuestionCount()
        );

        if (contents.isEmpty()) {
            throw new AlarmException(AlarmErrorCode.MISSION_CONTENT_NOT_FOUND);
        }

        // 정답 제외하고 반환
        return contents.stream()
                .map(MissionContentResponseDto::fromWithoutAnswer)
                .collect(Collectors.toList());
    }

    /**
     * 미션 답안 제출
     */
    @Transactional
    public MissionSubmitResponseDto submitMissionAnswer(Member member, Long alarmId, MissionSubmitDto requestDto) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        AlarmMission mission = alarm.getAlarmMission();

        if (mission == null) {
            throw new AlarmException(AlarmErrorCode.MISSION_NOT_FOUND);
        }

        // 문제 조회
        MissionContent content = missionContentRepository.findById(requestDto.getContentId())
                .orElseThrow(() -> new AlarmException(AlarmErrorCode.MISSION_CONTENT_NOT_FOUND));

        // 정답 확인
        boolean isCorrect = content.getAnswer().trim().equalsIgnoreCase(requestDto.getUserAnswer().trim());

        // 미션 기록 저장 (정답일 경우에만)
        if (isCorrect) {
            MissionHistory history = MissionHistory.builder()
                    .alarm(alarm)
                    .missionType(mission.getMissionType())
                    .isSuccess(true)
                    .attemptCount(requestDto.getAttemptCount())
                    .completedAt(LocalDateTime.now())
                    .build();
            missionHistoryRepository.save(history);
        }

        return MissionSubmitResponseDto.builder()
                .isCorrect(isCorrect)
                .isCompleted(isCorrect)
                .remainingQuestions(isCorrect ? 0 : mission.getQuestionCount() - 1)
                .message(isCorrect ? "정답입니다!" : "틀렸습니다. 다시 시도해주세요.")
                .build();
    }

    /**
     * 걷기 미션 진행률 업데이트
     */
    @Transactional
    public WalkProgressResponseDto updateWalkProgress(Member member, Long alarmId, WalkProgressDto requestDto) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        AlarmMission mission = alarm.getAlarmMission();

        if (mission == null || mission.getMissionType() != MissionType.WALK) {
            throw new AlarmException(AlarmErrorCode.MISSION_NOT_FOUND);
        }

        // 진행률 계산
        WalkProgressResponseDto response = WalkProgressDto.toResponse(
                requestDto.getCurrentDistance(),
                mission.getWalkGoalMeter()
        );

        // 미션 완료 시 기록 저장
        if (response.getIsCompleted()) {
            MissionHistory history = MissionHistory.builder()
                    .alarm(alarm)
                    .missionType(MissionType.WALK)
                    .isSuccess(true)
                    .attemptCount(1)
                    .completedAt(LocalDateTime.now())
                    .build();
            missionHistoryRepository.save(history);
        }

        return response;
    }

    /**
     * 내 미션 수행 기록 조회
     */
    public List<MissionHistoryResponseDto> getMyMissionHistory(Member member) {
        List<MissionHistory> histories = missionHistoryRepository.findByAlarm_Member_IdOrderByCompletedAtDesc(member.getId());
        return histories.stream()
                .map(MissionHistoryResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 특정 알람의 미션 기록 조회
     */
    public List<MissionHistoryResponseDto> getAlarmMissionHistory(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        List<MissionHistory> histories = missionHistoryRepository.findByAlarmOrderByCompletedAtDesc(alarm);
        return histories.stream()
                .map(MissionHistoryResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 내 알람 울림 기록 조회
     */
    public List<AlarmLogResponseDto> getMyAlarmLogs(Member member) {
        List<AlarmLog> logs = alarmLogRepository.findByAlarm_Member_IdOrderByTriggeredAtDesc(member.getId());
        return logs.stream()
                .map(AlarmLogResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 특정 알람의 울림 기록 조회
     */
    public List<AlarmLogResponseDto> getAlarmLogs(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);
        List<AlarmLog> logs = alarmLogRepository.findByAlarmOrderByTriggeredAtDesc(alarm);
        return logs.stream()
                .map(AlarmLogResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 알람 울림 기록 (트리거)
     */
    @Transactional
    public AlarmLogResponseDto triggerAlarm(Member member, Long alarmId) {
        Alarm alarm = findAlarmByIdAndMember(alarmId, member);

        AlarmLog log = AlarmLog.builder()
                .alarm(alarm)
                .triggeredAt(LocalDateTime.now())
                .snoozeCount(0)
                .build();

        AlarmLog savedLog = alarmLogRepository.save(log);
        return AlarmLogResponseDto.from(savedLog);
    }

    /**
     * 사운드 타입 유효성 검증 및 기본값 처리
     */
    private String validateAndGetSoundType(String soundType) {
        if (soundType == null || soundType.trim().isEmpty()) {
            return alarmSoundService.getDefaultSoundType();
        }

        if (!alarmSoundService.isValidSoundType(soundType)) {
            log.warn("Invalid sound type provided: {}, using default", soundType);
            return alarmSoundService.getDefaultSoundType();
        }

        return soundType.toUpperCase();
    }

    /**
     * 알람 조회 헬퍼 메서드
     */
    private Alarm findAlarmByIdAndMember(Long alarmId, Member member) {
        return alarmRepository.findByIdAndMemberWithDetails(alarmId, member)
                .orElseThrow(() -> new AlarmException(AlarmErrorCode.ALARM_NOT_FOUND));
    }
}