package Lumo.lumo_backend.domain.member.entity;

public enum MemberRole {
    ADMIN, USER;

    public String getAuthority() {
        return "ROLE_" + this.name(); // Security 권한 체크용 ROLE_추가
    }
}
