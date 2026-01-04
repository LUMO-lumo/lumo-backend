package Lumo.lumo_backend.domain.member.exception;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
