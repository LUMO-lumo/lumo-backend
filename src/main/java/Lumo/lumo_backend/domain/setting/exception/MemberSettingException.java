package Lumo.lumo_backend.domain.setting.exception;

import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.setting.status.MemberSettingErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;

public class MemberSettingException extends GeneralException {
    public MemberSettingException (MemberSettingErrorCode errorCode) {
        super(errorCode);
    }

}
