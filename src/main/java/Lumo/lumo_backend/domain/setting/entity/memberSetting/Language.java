package Lumo.lumo_backend.domain.setting.entity.memberSetting;

/**
 * 앱에서 지원하는 언어 목록
 */
public enum Language {
    KO("ko"),
    EN("en"),
    JA("ja");

    /**
     * ISO 639-1 준수 국가코드
     */
    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
