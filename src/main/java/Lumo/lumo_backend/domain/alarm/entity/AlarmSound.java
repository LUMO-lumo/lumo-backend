package Lumo.lumo_backend.domain.alarm.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 알람 벨소리 식별자 열거형
 * - 클라이언트 로컬 저장 mp3와 매핑되는 식별자만 관리
 * - 실제 파일은 iOS/Android 앱 번들에 포함
 */
@Getter
@RequiredArgsConstructor
public enum AlarmSound {

    // 기본 제공 사운드 식별자
    DEFAULT_BELL("기본 벨소리"),
    GENTLE_CHIME("부드러운 차임"),
    MORNING_BIRD("아침 새소리"),
    CALM_PIANO("잔잔한 피아노"),
    ENERGETIC_BEEP("활기찬 비프음"),
    SOFT_MELODY("부드러운 멜로디"),
    NATURE_SOUNDS("자연 소리"),
    CLASSIC_ALARM("클래식 알람"),
    JAZZ_WAKE("재즈 웨이크업"),
    OCEAN_WAVE("파도 소리");

    private final String displayName;

    /**
     * 기본 사운드인지 확인
     */
    public boolean isDefaultSound() {
        return this == DEFAULT_BELL;
    }

    /**
     * 표시 이름으로 벨소리 찾기
     */
    public static AlarmSound findByDisplayName(String displayName) {
        for (AlarmSound sound : values()) {
            if (sound.getDisplayName().equals(displayName)) {
                return sound;
            }
        }
        return DEFAULT_BELL; // 기본값 반환
    }
}