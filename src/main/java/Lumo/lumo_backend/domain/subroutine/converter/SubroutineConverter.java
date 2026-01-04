package Lumo.lumo_backend.domain.subroutine.converter;

import Lumo.lumo_backend.domain.routine.entity.Routine;
import Lumo.lumo_backend.domain.subroutine.dto.SubroutineRespDTO;

import java.util.List;
import java.util.stream.Collectors;

public class SubroutineConverter {

    public static List<SubroutineRespDTO.GetSubroutineDTO> toSubroutineDTO(Routine routine) {

        List<SubroutineRespDTO.GetSubroutineDTO> collect = routine.getSubroutineList().stream()
                .map(subroutine ->
                    SubroutineRespDTO.GetSubroutineDTO.builder()
                            .subroutineId(subroutine.getId())
                            .title(subroutine.getTitle())
                            .successCount(subroutine.getSuccessCount())
                            .isSuccess(subroutine.getIsSuccess())
                            .build()
                )
                .collect(Collectors.toList());
        return collect;
    }
}
