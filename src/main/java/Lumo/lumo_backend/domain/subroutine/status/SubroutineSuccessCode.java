package Lumo.lumo_backend.domain.subroutine.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SubroutineSuccessCode implements BaseCode {

    // 성공 응답 작성 예시 입니다.
    TEST_SUCCESS (HttpStatus.OK, "TEST2000", "테스트 성공 입니다."),

    CREATE_SUBROUTINE_SUCCESS(HttpStatus.OK, "SUBR2000", "서브루틴 생성에 성공했습니다."),
    CHECK_SUBROUTINE_SUCCESS(HttpStatus.OK, "SUBR2001", "서브루틴 체크에 성공했습니다."),
    DELETE_SUBROUTINE_SUCCESS(HttpStatus.OK, "SUBR2002", "서브루틴 삭제에 성공했습니다."),
    RENAME_SUBROUTINE_SUCCESS(HttpStatus.OK, "SUBR2003", "서브루틴 이름 변경에 성공했습니다.")

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