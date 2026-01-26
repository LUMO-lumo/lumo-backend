package Lumo.lumo_backend.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class MemberReqDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInRequestDTO {
        @NotNull
        @Email
        private String email;

        @NotNull
        private String password;

        @NotNull
        private String username;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginReqDTO {
        @NotNull
        @Email
        private String email;
        @NotNull
        private String password;
    }
}
