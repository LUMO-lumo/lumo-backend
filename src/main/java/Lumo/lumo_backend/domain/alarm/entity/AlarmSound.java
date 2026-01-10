package Lumo.lumo_backend.domain.alarm.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 알람 벨소리 열거형
 * - 카테고리별 벨소리 목록 관리
 */
@Getter
@RequiredArgsConstructor
public enum AlarmSound {

    // 차분한 카테고리
    CALM_MORNING_BREEZE("차분한", "아침 산들바람", "calm_morning_breeze.mp3"),
    CALM_SOFT_PIANO("차분한", "부드러운 피아노", "calm_soft_piano.mp3"),
    CALM_GENTLE_BELLS("차분한", "은은한 종소리", "calm_gentle_bells.mp3"),
    CALM_PEACEFUL_RAIN("차분한", "평화로운 빗소리", "calm_peaceful_rain.mp3"),
    CALM_MEDITATION("차분한", "명상 멜로디", "calm_meditation.mp3"),

    // 시끄러운 카테고리
    LOUD_ALARM_CLOCK("시끄러운", "전통 알람시계", "loud_alarm_clock.mp3"),
    LOUD_ROOSTER("시끄러운", "수탉 울음소리", "loud_rooster.mp3"),
    LOUD_EMERGENCY("시끄러운", "긴급 경보음", "loud_emergency.mp3"),
    LOUD_TRUCK_HORN("시끄러운", "트럭 경적", "loud_truck_horn.mp3"),
    LOUD_FIRE_ALARM("시끄러운", "화재 경보", "loud_fire_alarm.mp3"),

    // 경쾌한 카테고리
    UPBEAT_MORNING_JAZZ("경쾌한", "모닝 재즈", "upbeat_morning_jazz.mp3"),
    UPBEAT_POP_MELODY("경쾌한", "팝 멜로디", "upbeat_pop_melody.mp3"),
    UPBEAT_BIRDS_CHIRPING("경쾌한", "새소리", "upbeat_birds_chirping.mp3"),
    UPBEAT_GUITAR_STRUMMING("경쾌한", "기타 스트러밍", "upbeat_guitar_strumming.mp3"),
    UPBEAT_WHISTLE_TUNE("경쾌한", "휘파람 멜로디", "upbeat_whistle_tune.mp3"),

    // 자연 카테고리
    NATURE_OCEAN_WAVES("자연", "파도 소리", "nature_ocean_waves.mp3"),
    NATURE_FOREST_SOUNDS("자연", "숲속 소리", "nature_forest_sounds.mp3"),
    NATURE_STREAM_WATER("자연", "계곡물 소리", "nature_stream_water.mp3"),
    NATURE_WIND_CHIMES("자연", "바람 풍경소리", "nature_wind_chimes.mp3"),
    NATURE_THUNDER("자연", "천둥 소리", "nature_thunder.mp3");

    private final String category;
    private final String displayName;
    private final String fileName;

    /**
     * 카테고리별 벨소리 목록 조회
     */
    public static List<AlarmSound> getByCategory(String category) {
        return Arrays.stream(AlarmSound.values())
                .filter(sound -> sound.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    /**
     * 모든 카테고리 목록 조회
     */
    public static List<String> getAllCategories() {
        return Arrays.stream(AlarmSound.values())
                .map(AlarmSound::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 파일명으로 벨소리 찾기
     */
    public static AlarmSound findByFileName(String fileName) {
        return Arrays.stream(AlarmSound.values())
                .filter(sound -> sound.getFileName().equals(fileName))
                .findFirst()
                .orElse(null);
    }

    /**
     * 표시 이름으로 벨소리 찾기
     */
    public static AlarmSound findByDisplayName(String displayName) {
        return Arrays.stream(AlarmSound.values())
                .filter(sound -> sound.getDisplayName().equals(displayName))
                .findFirst()
                .orElse(null);
    }

    /**
     * 벨소리 파일 경로 반환
     * (실제 파일 저장 경로에 맞게 수정 필요)
     */
    public String getFilePath() {
        return "/sounds/alarm/" + this.fileName;
    }
}