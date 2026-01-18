package Lumo.lumo_backend.domain.routine.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RoutineSuccessCode implements BaseCode {

    // 성공 응답 작성 예시 입니다.
    TEST_SUCCESS (HttpStatus.OK, "TEST2000", "테스트 성공 입니다."),

    CREATE_ROUTINE_SUCCESS(HttpStatus.OK, "ROUTINE_2000", "루틴 생성에 성공했습니다."),
    GET_ROUTINE_SUCCESS(HttpStatus.OK, "ROUTINE_2001", "루틴 조회에 성공했습니다."),
    DELETE_ROUTINE_SUCCESS(HttpStatus.OK, "ROUTINE_2002", "루틴 삭제에 성공했습니다."),
    RENAME_ROUTINE_SUCCESS(HttpStatus.OK, "ROUTINE_2003", "루틴 이름 변경에 성공했습니다."),
    CHECK_ROUTINE_SUCCESS(HttpStatus.OK, "ROUTINE_2004", "루틴 체크에 성공했습니다."),


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