package Lumo.lumo_backend.domain.alarm.entity.exception;

import Lumo.lumo_backend.domain.alarm.entity.exception.code.AlarmErrorCode;
import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class AlarmException extends GeneralException {

    public AlarmException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public AlarmException(AlarmErrorCode errorCode) {
        super(errorCode);
    }
}