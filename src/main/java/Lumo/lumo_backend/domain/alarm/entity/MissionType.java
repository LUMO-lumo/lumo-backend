
package Lumo.lumo_backend.domain.alarm.entity;

/**
 * 알람 미션 유형
 */
public enum MissionType {
    NONE,       // 미션 없음
    MATH,       // 수학 문제 (ex: 8+24+7=?)
    OX_QUIZ,    // OX 퀴즈 (ex: 대한민국 수도는 서울이다)
    TYPING,     // 따라쓰기 (ex: 안녕하세요)
    WALK        // 걷기 미션 (GPS 기반 거리 측정)
}