package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.MemberTermsAgreementErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class MemberTermsAgreementException extends GeneralException {
    public MemberTermsAgreementException (MemberTermsAgreementErrorCode errorCode) {
        super(errorCode);
    }
}