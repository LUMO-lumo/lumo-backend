package Lumo.lumo_backend.global.exception;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public GeneralException(BaseErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorReasonDTO getReason (){
        return this.errorCode.getReason();
    }

    public ErrorReasonDTO getReasonHttpStatus (){
        return this.errorCode.getReasonHttpStatus();
    }

}
