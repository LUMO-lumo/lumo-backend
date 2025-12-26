package Lumo.lumo_backend.global.apiResponse.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO { // 에러 기본 DTO
    private HttpStatus httpStatus;

    private boolean isSuccess;
    private String code;
    private String message;

    public boolean getIsSuccess() {
        return isSuccess;
    }

}

