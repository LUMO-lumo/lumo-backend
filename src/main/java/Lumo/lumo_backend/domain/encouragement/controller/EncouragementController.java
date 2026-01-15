package Lumo.lumo_backend.domain.encouragement.controller;

import Lumo.lumo_backend.domain.encouragement.dto.EncouragementResponseDTO;
import Lumo.lumo_backend.domain.encouragement.entity.Encouragement;
import Lumo.lumo_backend.domain.encouragement.repository.EncouragementRepository;
import Lumo.lumo_backend.domain.encouragement.status.EncouragementSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encouragement")
@RequiredArgsConstructor
public class EncouragementController {

    private final EncouragementRepository encouragementRepository;

    @Operation(summary = "오늘의 한마디 조회")
    @GetMapping
    public APIResponse<EncouragementResponseDTO> getRandom(){

        Encouragement encouragement = encouragementRepository.findRandomOne();

        return APIResponse.onSuccess(EncouragementResponseDTO.from(encouragement), EncouragementSuccessCode.GET_ENCOURAGEMENT_SUCCESS);
    }

}
