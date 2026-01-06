package Lumo.lumo_backend.domain.routine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RoutineRequestDTO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetRoutineReqDTO {
        private List<SubRoutineDTO> subRoutineList;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SubRoutineDTO {
        private String title;
        private Integer successCount;
        private Boolean isSuccess; //
    }
}
