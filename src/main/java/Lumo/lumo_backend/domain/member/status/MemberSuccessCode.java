package Lumo.lumo_backend.domain.member.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseCode {

    // 성공 응답 작성 예시 입니다.
    TEST_SUCCESS (HttpStatus.OK, "TEST2000", "테스트 성공 입니다."),

    EMAIL_DUPLICATE_CHECK_SUCCESS (HttpStatus.OK, "MEMBER2001", "이메일 중복 검사 성공 입니다."),
    VERIFY_CODE_SUCCESS (HttpStatus.OK, "MEMBER2002", "인증 코드 검증 성공 입니다."),
    SIGN_IN_SUCCESS (HttpStatus.OK, "MEMBER2003", "회원가입 로직 성공 입니다."),
    REQ_CODE_SUCCESS (HttpStatus.OK, "MEMBER2004", "인증 코드 발송 성공 입니다."),
    GET_LOGIN_SUCCESS (HttpStatus.OK, "MEMBER2005", "로그인 방식 조회 성공입니다."),
    LOGIN_SUCCESS (HttpStatus.OK, "MEMBER2005", "로그인 요청 성공입니다."),

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