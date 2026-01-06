package Lumo.lumo_backend.domain.setting.controller;


import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/stats")
public class StatController {

    @GetMapping
    public APIResponse<Object> getStat() {
        return null;
    }
}
