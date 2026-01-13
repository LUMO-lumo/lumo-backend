package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.MemberDeviceErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class MemberDeviceException extends GeneralException {
    public MemberDeviceException (MemberDeviceErrorCode errorCode) {
        super(errorCode);
    }
}
