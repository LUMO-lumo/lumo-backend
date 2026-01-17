package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.MemberStatErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class MemberStatException extends GeneralException {
    public MemberStatException (MemberStatErrorCode errorCode) {
        super(errorCode);
    }
}