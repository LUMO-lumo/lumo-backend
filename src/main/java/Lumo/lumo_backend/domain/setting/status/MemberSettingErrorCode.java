package Lumo.lumo_backend.domain.setting.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSettingErrorCode implements BaseErrorCode {


    // 예외 작성 예시 입니다.
    TEST_EXCEPTION (HttpStatus.BAD_REQUEST, "TEST4000", "테스트 예외 입니다."),

    // 여기서부터 이어서 작성해주시기 바랍니다.
//    INVALID_THEME(HttpStatus.BAD_REQUEST, "SETTING4000","지원하지 않는 테마입니다."),
//    INVALID_LANGUAGE(HttpStatus.BAD_REQUEST, "SETTING4001","지원하지 않는 언어입니다."),
//    INVALID_BRIEFING_VOICE(HttpStatus.BAD_REQUEST, "SETTING4002","지원하지 않는 브리핑 목소리입니다."),
//    INVALID_MISSION_LEVEL(HttpStatus.BAD_REQUEST, "SETTING4003","지원하지 않는 미션 레벨입니다."),

    UNAUTHORIZED_SETTING_ACCESS(HttpStatus.FORBIDDEN, "SETTING4031","권한이 부족합니다."),

    SETTING_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "SETTING5000", "사용자의 설정이 존재하지 않습니다."),
    SETTING_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "SETTING5001","업데이트에 실패하였습니다."),
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}

