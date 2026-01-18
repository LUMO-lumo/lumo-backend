package Lumo.lumo_backend.domain.routine.dto;

import Lumo.lumo_backend.domain.subroutine.dto.SubroutineRespDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class RoutineRespDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRoutineDTO {
        private Long id;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetRoutineDTO {
        private Long routineId;
        private String routineTitle;
        private List<SubroutineRespDTO.GetSubroutineDTO>  subroutineList;
    }
}
