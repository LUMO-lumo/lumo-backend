package Lumo.lumo_backend.domain.member.dto;

import Lumo.lumo_backend.domain.member.entity.Login;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberRespDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CheckDuplicateRespDTO {
        private Login login;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VerifyCodeRespDTO {
        private Boolean isSuccess;
    }
}
