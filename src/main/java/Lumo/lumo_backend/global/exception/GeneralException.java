package Lumo.lumo_backend.global.exception;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorReasonDTO getReason (){
        return this.errorCode.getReason();
    }

    public ErrorReasonDTO getReasonHttpStatus (){
        return this.errorCode.getReasonHttpStatus();
    }

}
