package Lumo.lumo_backend.domain.encouragement.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum EncouragementSuccessCode implements BaseCode {
    GET_ENCOURAGEMENT_SUCCESS(HttpStatus.OK, "ENCOURAGEMENT_200_1", "오늘의 한마디 조회에 성공했습니다.")
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
