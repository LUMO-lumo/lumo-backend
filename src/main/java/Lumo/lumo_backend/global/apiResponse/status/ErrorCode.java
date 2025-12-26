package Lumo.lumo_backend.global.apiResponse.status;


import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode implements BaseErrorCode {

    // 예외 작성 예시 입니다.
    TEST_EXCEPTION (HttpStatus.BAD_REQUEST, "TEST4000", "테스트 예외 입니다."),

    // 여기서부터 이어서 작성해주시기 바랍니다.

    /// USER
    USER_TEST_EXCEPTION (HttpStatus.BAD_REQUEST, "USER1234", "테스트 사용자 API 예외 입니다."),


    /// ALARM
    ALARM_TEST_EXCEPTION (HttpStatus.BAD_REQUEST, "ALARM1234", "테스트 알람 API 예외 입니다."),


    /// SERVER
    INTERNAL_SERVER_ERROR (HttpStatus.INTERNAL_SERVER_ERROR, "SERVER5000", "서버 에러 입니다. 관리자에게 문의 부탁 드립니다")


    ;




    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}