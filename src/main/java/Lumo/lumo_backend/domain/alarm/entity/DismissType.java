
package Lumo.lumo_backend.domain.alarm.entity;

/**
 * 알람 해제 방식
 */
public enum DismissType {
    MISSION,    // 미션 완료로 해제
    SNOOZE,     // 스누즈로 연기
    MANUAL      // 수동 해제 (그냥 끄기)
}