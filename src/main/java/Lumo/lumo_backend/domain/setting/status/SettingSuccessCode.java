package Lumo.lumo_backend.domain.setting.status;

import Lumo.lumo_backend.global.apiResponse.basecode.BaseCode;
import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SettingSuccessCode implements BaseCode {

    // 성공 응답 작성 예시 입니다.
    TEST_SUCCESS (HttpStatus.OK, "TEST_2000", "테스트 성공 입니다."),



    // 여기서부터 이어서 작성해주시기 바랍니다.
    DEVICE_CREATE_SUCCESS(HttpStatus.CREATED, "DEVICE_2010", "기기 생성 성공"),
    DEVICE_LIST_SUCCESS(HttpStatus.OK, "DEVICE_2000", "기기 목록 조회 성공"),
    DEVICE_UPDATE_SUCCESS(HttpStatus.OK, "DEVICE_2001", "기기 수정 성공"),
    DEVICE_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "DEVICE_2040", "기기 삭제 성공"),

    NOTICE_CREATE_SUCCESS(HttpStatus.CREATED, "NOTICE_2010", "공지사항 생성 성공"),
    NOTICE_LIST_SUCCESS(HttpStatus.OK, "NOTICE_2000", "공지사항 목록 조회 성공"),
    NOTICE_DETAIL_SUCCESS(HttpStatus.OK, "NOTICE_2001", "공지사항 상세 조회 성공"),
    NOTICE_UPDATE_SUCCESS(HttpStatus.OK, "NOTICE_2002", "공지사항 수정 성공"),
    NOTICE_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "NOTICE_2040", "공지사항 삭제 성공"),

    FEEDBACK_CREATE_SUCCESS(HttpStatus.CREATED, "FEEDBACK_2010", "피드백 생성 성공"),
    FEEDBACK_LIST_SUCCESS(HttpStatus.OK, "FEEDBACK_2000", "피드백 목록 조회 성공"),
    FEEDBACK_DETAIL_SUCCESS(HttpStatus.OK, "FEEDBACK_2001", "피드백 상세 조회 성공"),
    FEEDBACK_UPDATE_SUCCESS(HttpStatus.OK, "FEEDBACK_2002", "피드백 수정 성공"),

    SETTING_DETAIL_SUCCESS(HttpStatus.OK, "SETTING_2000", "설정 조회 성공"),
    SETTING_UPDATE_SUCCESS(HttpStatus.OK, "SETTING_2001", "설정 수정 성공"),

    STAT_DETAIL_SUCCESS(HttpStatus.OK, "STAT_2000", "통계 조회 성공"),
//    STAT_UPDATE_SUCCESS(HttpStatus.OK, "STAT_2001", "통계 수정 성공"),

//    INQUIRY_CREATE_SUCCESS(HttpStatus.CREATED, "INQUIRY_2010", "문의사항 생성 성공"),
//    INQUIRY_LIST_SUCCESS(HttpStatus.OK, "INQUIRY_2000", "문의사항 목록 조회 성공"),
//    INQUIRY_DETAIL_SUCCESS(HttpStatus.OK, "INQUIRY_2001", "문의사항 상세 조회 성공"),
//    INQUIRY_UPDATE_SUCCESS(HttpStatus.OK, "INQUIRY_2002", "문의사항 수정 성공"),
//
//    ADMIN_REPLY_CREATE_SUCCESS(HttpStatus.CREATED, "ADMIN_REPLY_2010", "관리자 답변 생성 성공"),
//    ADMIN_REPLY_UPDATE_SUCCESS(HttpStatus.OK, "ADMIN_REPLY_2000", "관리자 답변 수정 성공"),
//
//    TERMS_AGREEMENT_CREATE_SUCCESS(HttpStatus.CREATED, "TERMS_AGREEMENT_2010", "약관 동의 생성 성공"),
//    TERMS_AGREEMENT_LIST_SUCCESS(HttpStatus.OK, "TERMS_AGREEMENT_2000", "약관 동의 목록 조회 성공"),
//    TERMS_AGREEMENT_UPDATE_SUCCESS(HttpStatus.OK, "TERMS_AGREEMENT_2001", "약관 동의 수정 성공"),
//
//
//    TERMS_VERSION_CREATE_SUCCESS(HttpStatus.CREATED, "TERMS_VERSION_2010", "약관 버전 생성 성공"),
//    TERMS_VERSION_UPDATE_SUCCESS(HttpStatus.OK, "TERMS_VERSION_2001", "약관 버전 수정 성공"),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;


    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .isSuccess(true)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(status)
                .isSuccess(true)
                .code(this.name())
                .message(message)
                .build();
    }

    @Override
    public String getCodeName() {
        return this.name();  // enum 객체의 이름 반환
    }
}