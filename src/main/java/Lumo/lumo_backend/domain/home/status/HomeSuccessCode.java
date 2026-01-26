package Lumo.lumo_backend.domain.home.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum HomeSuccessCode implements BaseCode {

    HOME_GET_SUCCESS(HttpStatus.OK, "HOME_2000", "홈 페이지 조회 성공"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .isSuccess(true)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(status)
                .isSuccess(true)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public String getCodeName() {
        return this.name();  // enum 객체의 이름 반환
    }
}
