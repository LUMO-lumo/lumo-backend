package Lumo.lumo_backend.domain.setting.controller;

import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 설정 컨트롤러
 */
@RestController
@Slf4j
@RequestMapping("/api/setting")
public class SettingController {

    @GetMapping
    public APIResponse<Object> getMemberSetting() {
        return null;
    }

    @PatchMapping
    public APIResponse<Object> updateMemberSetting() {
        return null;
    }



}
