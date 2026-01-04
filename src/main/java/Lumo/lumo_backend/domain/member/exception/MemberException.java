package Lumo.lumo_backend.domain.member.exception;

import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(MemberErrorCode errorCode) {
        super(errorCode);
    }
}
