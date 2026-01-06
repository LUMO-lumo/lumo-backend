package Lumo.lumo_backend.domain.subroutine.controller;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.routine.status.RoutineSuccessCode;
import Lumo.lumo_backend.domain.subroutine.service.SubroutineService;
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

    private final SubroutineService subroutineService;

    /*
     *
     * 관리하고자 하는 서브루틴을 보유하고 있는 사용자 인지에 대한 검증이 필요!
     *
     * */

    @PostMapping
    public APIResponse<Long> createSubroutine(@AuthenticationPrincipal Member member, @RequestParam ("subroutineId") Long routineId, @RequestParam("title") String title) {
        return APIResponse.onSuccess(subroutineService.createSubroutine(member, routineId, title), RoutineSuccessCode.RENAME_ROUTINE_SUCCESS); /// id 반환 필요!
    }

    @DeleteMapping
    public APIResponse<Object> deleteSubroutine(@AuthenticationPrincipal Member member, @RequestParam ("subroutineId") Long subroutineId) {
        subroutineService.deleteSubroutine(member, subroutineId);
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

    @PatchMapping
    public APIResponse<Object> renameSubroutine(@AuthenticationPrincipal Member member, @RequestParam ("id") Long subroutineId, @RequestParam("title") String title) {
        subroutineService.renameSubroutine(member, subroutineId, title);
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

    @PostMapping("/check")
    public APIResponse<Object> checkSubroutine (@AuthenticationPrincipal Member member, @RequestParam ("id") Long subroutineId) {
        subroutineService.checkSubroutine(member, subroutineId);
        return APIResponse.onSuccess(null, RoutineSuccessCode.RENAME_ROUTINE_SUCCESS);
    }

}
