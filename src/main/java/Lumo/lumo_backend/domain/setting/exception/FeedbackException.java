package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.FeedbackErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class FeedbackException extends GeneralException {
    public FeedbackException (FeedbackErrorCode errorCode) {
        super(errorCode);
    }
}
