package Lumo.lumo_backend.domain.routine.controller;

import Lumo.lumo_backend.domain.routine.service.RoutineService;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/routine")
@RequiredArgsConstructor
public class RoutineController {

    private RoutineService routineService;

    @PostMapping
    public APIResponse<Object> createRoutine (@RequestParam("memberId") Long memberId, @RequestParam("title") String title){
        routineService.createRoutine(memberId, title);
        return APIResponse.onSuccess(null, );
    }

    @GetMapping
    public APIResponse<Object> getRoutine (@RequestParam("memberId") Long memberId){
        routineService.getRoutine(memberId);
        return APIResponse.onSuccess(null, );
    }

    @DeleteMapping
    public APIResponse<Object> deleteRoutine(@RequestParam("routineId") Long routineId){
        routineService.deleteRoutine(routineId);
        return APIResponse.onSuccess(null, );
    }

    @PatchMapping
    public APIResponse<Object> renameRoutine(@RequestParam("routineId") Long routineId, @RequestParam("title") String title) {
        routineService.renameRoutine(routineId, title);
        return APIResponse.onSuccess(null, );
    }

}