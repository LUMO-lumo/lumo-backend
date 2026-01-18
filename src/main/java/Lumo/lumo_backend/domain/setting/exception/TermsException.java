package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.TermsErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class TermsException extends GeneralException {
    public TermsException (TermsErrorCode errorCode) {
        super(errorCode);
    }
}
