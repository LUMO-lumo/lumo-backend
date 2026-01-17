package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.NoticeErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class NoticeException extends GeneralException {
    public NoticeException (NoticeErrorCode errorCode) {
        super(errorCode);
    }
}
