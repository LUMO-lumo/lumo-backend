package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.setting.status.SettingErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class SettingException extends GeneralException {
    public SettingException (SettingErrorCode errorCode) {
        super(errorCode);
    }
}
