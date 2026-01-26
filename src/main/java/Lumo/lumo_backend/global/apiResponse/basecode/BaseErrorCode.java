package Lumo.lumo_backend.global.apiResponse.basecode;


import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;

public interface BaseErrorCode {

    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();

    String getCode ();

    String getMessage();

    String getCodeName();

}
