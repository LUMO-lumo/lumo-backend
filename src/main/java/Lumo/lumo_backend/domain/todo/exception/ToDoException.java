package Lumo.lumo_backend.domain.todo.exception;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class ToDoException extends GeneralException {
    public ToDoException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
