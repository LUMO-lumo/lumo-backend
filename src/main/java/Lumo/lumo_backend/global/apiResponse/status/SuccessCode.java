package Lumo.lumo_backend.global.apiResponse.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode implements BaseCode {

    // 성공 응답 작성 예시 입니다.
    TEST_SUCCESS (HttpStatus.OK, "TEST2000", "테스트 성공 입니다."),

    // 이어서 작성해주시기 바랍니다


    ///  USER
    USER_TEST_SUCCESS (HttpStatus.OK, "USER2000", "테스트 성공 입니다."),


    ///  ALARM
    ALARM_TEST_SUCCESS (HttpStatus.OK, "ALARM2000", "테스트 성공 입니다."),

    ;

    private final HttpStatus status;
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
                .httpStatus(status)
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }
}
