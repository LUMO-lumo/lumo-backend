package Lumo.lumo_backend.domain.subroutine.exception;

import Lumo.lumo_backend.domain.routine.status.RoutineErrorCode;
import Lumo.lumo_backend.domain.subroutine.status.SubroutineErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class SubroutineException extends GeneralException {
    public SubroutineException(SubroutineErrorCode errorCode) {
        super(errorCode);
    }
}
