package Lumo.lumo_backend.domain.home.dto;

import Lumo.lumo_backend.domain.member.dto.MemberRespDTO.GetMissionRecordRespDTO;
import java.util.List;
import lombok.Builder;

@Builder
public record HomeResponseDTO(
        String encouragement,
        List<String> todo,
        GetMissionRecordRespDTO missionRecord
) {
}