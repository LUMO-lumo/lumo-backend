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
    public static class GetLoginDTO {
        private Login login;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SimpleAPIRespDTO {
        private Boolean isSuccess;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginRespDTO {
        private Boolean isSuccess;
        private String accessToken;
    }
}
