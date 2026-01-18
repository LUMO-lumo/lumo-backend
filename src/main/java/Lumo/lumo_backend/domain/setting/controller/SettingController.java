package Lumo.lumo_backend.domain.setting.controller;

import Lumo.lumo_backend.domain.setting.dto.MemberSettingResponseDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingUpdateRequestDTO;
import Lumo.lumo_backend.domain.setting.service.MemberSettingService;
import Lumo.lumo_backend.domain.setting.status.MemberSettingSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.apiResponse.status.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * 설정 컨트롤러
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/setting")
public class SettingController {

    private final MemberSettingService memberSettingService;

    @GetMapping
    public APIResponse<MemberSettingResponseDTO> get() {
        Long memberId = 1L;

        return APIResponse.onSuccess(memberSettingService.get(memberId), MemberSettingSuccessCode.SETTING_GET_SUCCESS);
    }


    @PatchMapping
    public APIResponse<Void> update(
            @RequestBody MemberSettingUpdateRequestDTO request
    ) {
        Long memberId = 1L;
        memberSettingService.update(memberId, request);
        return APIResponse.onSuccess(null, MemberSettingSuccessCode.SETTING_UPDATE_SUCCESS);
    }


}
