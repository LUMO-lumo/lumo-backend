package Lumo.lumo_backend.domain.routine.exception;

import Lumo.lumo_backend.domain.routine.status.RoutineErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class RoutineException extends GeneralException {
    public RoutineException(RoutineErrorCode errorCode) {
        super(errorCode);
    }
}
