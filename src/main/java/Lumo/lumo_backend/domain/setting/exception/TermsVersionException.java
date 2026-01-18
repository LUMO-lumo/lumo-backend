package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.TermsVersionErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class TermsVersionException extends GeneralException {
    public TermsVersionException (TermsVersionErrorCode errorCode) {
        super(errorCode);
    }
}
