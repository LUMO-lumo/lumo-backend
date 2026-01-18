package Lumo.lumo_backend.domain.alarm.entity.controller;

import Lumo.lumo_backend.domain.alarm.entity.dto.MissionContentCreateDto;
import Lumo.lumo_backend.domain.alarm.entity.dto.MissionContentResponseDto;
import Lumo.lumo_backend.domain.alarm.entity.status.AlarmSuccessCode;
import Lumo.lumo_backend.domain.alarm.entity.service.MissionContentService;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/missions/content")
@RequiredArgsConstructor
@Tag(name = "미션 문제 관리 API")
public class MissionContentController {

    private final MissionContentService missionContentService;

    /**
     * 미션 문제 등록
     * POST /api/missions/content
     */
    @PostMapping
    @Operation(summary = "미션 문제 등록 API", description = "미션 문제 은행에 새로운 문제를 등록하는 API입니다. 미션 타입, 난이도, 문제, 정답을 포함해야 합니다.")
    public APIResponse<MissionContentResponseDto> createMissionContent(
            @Valid @RequestBody MissionContentCreateDto requestDto
    ) {
        MissionContentResponseDto response = missionContentService.createMissionContent(requestDto);
        return APIResponse.onSuccess(response, AlarmSuccessCode.MISSION_CONTENT_CREATED);
    }

    /**
     * 미션 문제 삭제
     * DELETE /api/missions/content/{contentId}
     */
    @DeleteMapping("/{contentId}")
    @Operation(summary = "미션 문제 삭제 API", description = "미션 문제 은행에서 특정 문제를 삭제하는 API입니다.")
    public APIResponse<Void> deleteMissionContent(@PathVariable Long contentId) {
        missionContentService.deleteMissionContent(contentId);
        return APIResponse.onSuccess(null, AlarmSuccessCode.MISSION_CONTENT_DELETED);
    }
}
