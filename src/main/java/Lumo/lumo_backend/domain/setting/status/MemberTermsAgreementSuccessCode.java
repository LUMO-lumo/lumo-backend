package Lumo.lumo_backend.domain.setting.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberTermsAgreementSuccessCode implements BaseCode {

    // 성공 응답 작성 예시 입니다.
    TEST_SUCCESS (HttpStatus.OK, "TEST_2000", "테스트 성공 입니다."),

    SETTING_UPDATE_SUCCESS(HttpStatus.OK, "SETTING_2000", "설정 업데이트에 성공하였습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(status)
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }
}