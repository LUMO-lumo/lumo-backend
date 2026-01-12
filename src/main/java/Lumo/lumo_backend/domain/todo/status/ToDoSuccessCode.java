package Lumo.lumo_backend.domain.todo.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ToDoSuccessCode implements BaseCode {

    CREATE_TODO_SUCCESS(HttpStatus.OK, "TODO_200_1", "할 일 생성에 성공했습니다."),
    UPDATE_TODO_SUCCESS(HttpStatus.OK, "TODO_200_2", "할 일 수정에 성공했습니다."),
    DELETE_TODO_SUCCESS(HttpStatus.OK, "TODO_200_3", "할 일 삭제에 성공했습니다."),
    GET_TODO_SUCCESS(HttpStatus.OK, "TODO_200_4", "할 일 조회에 성공했습니다.")
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
