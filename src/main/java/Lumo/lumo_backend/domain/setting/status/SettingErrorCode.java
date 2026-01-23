package Lumo.lumo_backend.domain.setting.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.basecode.BaseErrorCode;
import Lumo.lumo_backend.global.apiResponse.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SettingErrorCode implements BaseErrorCode {

    // 예외 작성 예시 입니다.
    TEST_EXCEPTION (HttpStatus.BAD_REQUEST, "TEST_4000", "테스트 예외 입니다."),

    // 여기서부터 이어서 작성해주시기 바랍니다.
    DEVICE_NOT_FOUND(HttpStatus.NOT_FOUND, "DEVICE_4040", "기기 없음"),
    NOTICE_NOT_FOUND(HttpStatus.NOT_FOUND, "NOTICE_4040", "공지 없음"),
    FEEDBACK_NOT_FOUND(HttpStatus.NOT_FOUND, "FEEDBACK_4040", "피드백 없음"),
    SETTING_NOT_FOUND(HttpStatus.NOT_FOUND, "SETTING_4040", "설정 없음"),
    STAT_NOT_FOUND(HttpStatus.NOT_FOUND, "STAT_4040", "통계 없음"),
//    INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "INQUIRY_4040", "문의사항 없음")
    ;


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .isSuccess(false)
                .httpStatus(httpStatus)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public String getCodeName() {
        return this.name();  // enum 객체의 이름 반환
    }
}
