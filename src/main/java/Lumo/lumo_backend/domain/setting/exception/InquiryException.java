package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.InquiryErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class InquiryException extends GeneralException {
    public InquiryException (InquiryErrorCode errorCode) {
        super(errorCode);
    }
}