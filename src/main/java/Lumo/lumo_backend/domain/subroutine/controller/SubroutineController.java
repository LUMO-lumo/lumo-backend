package Lumo.lumo_backend.domain.subroutine.controller;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.routine.status.RoutineSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/subroutine")
@RequiredArgsConstructor
public class SubroutineController {

    @PostMapping
    public APIResponse<Object> createSubroutine(@AuthenticationPrincipal Member member, @RequestParam ("subroutineId") Long routineId, @RequestParam("title") String title) {
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS); /// id 반환 필요!
    }

    @DeleteMapping
    public APIResponse<Object> deleteSubroutine(@AuthenticationPrincipal Member member, @RequestParam ("subroutineId") Long subroutineId) {
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

    @PatchMapping
    public APIResponse<Object> renameSubroutine(@AuthenticationPrincipal Member member, @RequestParam ("id") Long subroutineId, @RequestParam("title") String title) {
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

    @PostMapping
    public APIResponse<Object> checkSubroutine (@AuthenticationPrincipal Member member, @RequestParam ("id") Long routineId, @RequestParam("title") String title) {
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

}
