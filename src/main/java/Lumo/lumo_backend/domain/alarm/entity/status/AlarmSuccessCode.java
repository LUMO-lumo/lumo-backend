package Lumo.lumo_backend.domain.alarm.entity.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AlarmSuccessCode implements BaseCode {

    // 알람 생성/조회/수정/삭제
    ALARM_CREATED(HttpStatus.CREATED, "ALARM_2001", "알람이 성공적으로 생성되었습니다."),
    ALARM_RETRIEVED(HttpStatus.OK, "ALARM_2002", "알람 조회에 성공했습니다."),
    ALARM_LIST_RETRIEVED(HttpStatus.OK, "ALARM_2003", "알람 목록 조회에 성공했습니다."),
    ALARM_UPDATED(HttpStatus.OK, "ALARM_2004", "알람이 성공적으로 수정되었습니다."),
    ALARM_DELETED(HttpStatus.OK, "ALARM_2005", "알람이 성공적으로 삭제되었습니다."),

    // 알람 상태 변경
    ALARM_TOGGLED(HttpStatus.OK, "ALARM_2011", "알람 상태가 변경되었습니다."),
    ALARM_ENABLED(HttpStatus.OK, "ALARM_2012", "알람이 활성화되었습니다."),
    ALARM_DISABLED(HttpStatus.OK, "ALARM_2013", "알람이 비활성화되었습니다."),

    // 반복 요일 설정
    REPEAT_DAYS_RETRIEVED(HttpStatus.OK, "ALARM_2021", "반복 요일 조회에 성공했습니다."),
    REPEAT_DAYS_UPDATED(HttpStatus.OK, "ALARM_2022", "반복 요일이 성공적으로 설정되었습니다."),

    // 알람 로그
    ALARM_LOG_RETRIEVED(HttpStatus.OK, "ALARM_2031", "알람 기록 조회에 성공했습니다."),
    ALARM_TRIGGERED(HttpStatus.OK, "ALARM_2032", "알람 울림이 기록되었습니다."),
    ALARM_DISMISSED(HttpStatus.OK, "ALARM_2033", "알람 해제가 기록되었습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .isSuccess(true)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}