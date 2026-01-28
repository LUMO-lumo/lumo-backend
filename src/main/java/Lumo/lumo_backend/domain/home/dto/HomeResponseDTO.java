package Lumo.lumo_backend.domain.home.dto;

import Lumo.lumo_backend.domain.member.dto.MemberRespDTO.GetMissionRecordRespDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import java.util.List;
import lombok.Builder;

@Builder
public record HomeResponseDTO(
        String encouragement,
        List<ToDoResponseDTO> todo,
        GetMissionRecordRespDTO missionRecord
) {
}