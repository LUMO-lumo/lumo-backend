package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class InquiryException extends GeneralException {
    public InquiryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
