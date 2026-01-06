package Lumo.lumo_backend.domain.setting.controller;


import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 기기 컨트롤러
 */
@RestController
@Slf4j
@RequestMapping("/api/devices")
public class DeviceController {

    @GetMapping
    public APIResponse<Object> getMemberDevice() {
        return null;
    }

    @PostMapping
    public APIResponse<Object> registerMemberDevice() {
        return null;
    }

    @PatchMapping("/{deviceId}")
    public APIResponse<Object> updateMemberDevice() {
        return null;
    }

    @DeleteMapping("/{deviceId}")
    public APIResponse<Object> deleteMemberDevice() {
        return null;
    }


}
