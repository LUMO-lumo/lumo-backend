package Lumo.lumo_backend.domain.subroutine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SubroutineRespDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetSubroutineDTO{
        private String title; // 각 서브루틴 제목
        private Integer successCount = 0; // 연속 성공 횟수
        private Boolean isSuccess;
    }
}
