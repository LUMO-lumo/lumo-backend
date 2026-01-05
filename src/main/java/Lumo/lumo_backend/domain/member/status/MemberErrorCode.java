package Lumo.lumo_backend.domain.member.status;


import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // 예외 작성 예시 입니다.
    TEST_EXCEPTION (HttpStatus.BAD_REQUEST, "TEST4000", "테스트 예외 입니다."),

    // 여기서부터 이어서 작성해주시기 바랍니다.
    CANT_FOUND_MEMBER(HttpStatus.BAD_REQUEST, "MEMBER4000", "알 수 없는 사용자입니다.")
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