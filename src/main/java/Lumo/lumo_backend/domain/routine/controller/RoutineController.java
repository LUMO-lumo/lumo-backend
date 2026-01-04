package Lumo.lumo_backend.domain.routine.controller;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.routine.dto.RoutineRespDTO;
import Lumo.lumo_backend.domain.routine.service.RoutineService;
import Lumo.lumo_backend.domain.routine.status.RoutineSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/routine")
@RequiredArgsConstructor
public class RoutineController {

    private final RoutineService routineService;

    @PostMapping
    public APIResponse<Object> createRoutine (@RequestParam("memberId") Long memberId, @RequestParam("title") String title){
        routineService.createRoutine(memberId, title);
        return APIResponse.onSuccess(null, RoutineSuccessCode.CREATE_ROUTINE_SUCCESS); // 루틴 첫 생성 시에는 서브루틴이 없으므로 null 반환
    }

    @GetMapping
    public APIResponse<List<RoutineRespDTO.GetRoutineDTO>> getRoutine (@RequestParam("memberId") Long memberId){
        List<RoutineRespDTO.GetRoutineDTO> routineList = routineService.getRoutine(memberId);
        return APIResponse.onSuccess(routineList, RoutineSuccessCode.GET_ROUTINE_SUCCESS);
    }

    @DeleteMapping
    public APIResponse<Object> deleteRoutine(@AuthenticationPrincipal Member member, @RequestParam("routineId") Long routineId){
        routineService.deleteRoutine(member, routineId);
        return APIResponse.onSuccess(null, RoutineSuccessCode.DELETE_ROUTINE_SUCCESS);
    }

    @PatchMapping
    public APIResponse<Object> renameRoutine(@AuthenticationPrincipal Member member, @RequestParam("routineId") Long routineId, @RequestParam("title") String title) {
        routineService.renameRoutine(member, routineId, title);
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

}