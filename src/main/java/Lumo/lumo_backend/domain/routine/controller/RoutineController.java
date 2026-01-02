package Lumo.lumo_backend.domain.routine.controller;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/routine")
@RequiredArgsConstructor
public class RoutineController {

    @PostMapping
    public APIResponse<Object> createRoutine (@RequestParam("userId") Long userId,  @RequestParam("title") String title){
        return APIResponse.onSuccess();
    }

    @GetMapping
    public APIResponse<Object> getRoutine (@RequestParam("userId") Long userId){
        return APIResponse.onSuccess();
    }

    @DeleteMapping
    public APIResponse<Object> deleteRoutine(@RequestParam("routineId") Long routineId){
        return APIResponse.onSuccess();
    }

    @PatchMapping
    public APIResponse<Object> renameRoutine(@RequestParam("routineId") Long routineId, @RequestParam("title") String title) {
        return APIResponse.onSuccess();
    }

}