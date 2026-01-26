package Lumo.lumo_backend.domain.alarm.entity.exception.code;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AlarmErrorCode implements BaseErrorCode {

    // 알람 관련 에러
    ALARM_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM_5001", "알람을 찾을 수 없습니다."),
    ALARM_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM_5002", "알람 울림 기록을 찾을 수 없습니다."),
    ALARM_UNAUTHORIZED(HttpStatus.FORBIDDEN, "ALARM_5003", "해당 알람에 대한 권한이 없습니다."),
    INVALID_ALARM_TIME(HttpStatus.BAD_REQUEST, "ALARM_5005", "유효하지 않은 알람 시간입니다."),
    INVALID_REPEAT_DAYS(HttpStatus.BAD_REQUEST, "ALARM_5006", "유효하지 않은 반복 요일입니다."),
    SNOOZE_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "ALARM_5008", "스누즈 횟수를 초과했습니다."),

    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM_5011", "미션 설정을 찾을 수 없습니다."),
    MISSION_CONTENT_NOT_FOUND(HttpStatus.NOT_FOUND, "ALARM_5012", "미션 문제를 찾을 수 없습니다."),
    MISSION_ANSWER_INCORRECT(HttpStatus.BAD_REQUEST, "ALARM_5013", "틀린 답입니다."),
    MISSION_NOT_STARTED(HttpStatus.BAD_REQUEST, "ALARM_5014", "미션이 시작되지 않았습니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "ALARM_5015", "이미 완료된 미션입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public String getCodeName() {
        return this.name();  // enum 객체의 이름 반환
    }
}