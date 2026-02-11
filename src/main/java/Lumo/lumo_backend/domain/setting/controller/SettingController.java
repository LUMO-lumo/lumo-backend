package Lumo.lumo_backend.domain.setting.controller;

import Lumo.lumo_backend.domain.setting.dto.MemberSettingResDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberSettingUpdateReqDTO;
import Lumo.lumo_backend.domain.setting.service.MemberSettingService;
import Lumo.lumo_backend.domain.setting.status.SettingSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.security.userDetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @Operation(summary = "설정 조회 API", description = "사용자가 서비스 내에서의 설정을 조회하는 API 입니다.")
    public APIResponse<MemberSettingResDTO> get(
            @AuthenticationPrincipal CustomUserDetails userDetail
    ) {

        return APIResponse.onSuccess(memberSettingService.get(userDetail.getMember().getId()), SettingSuccessCode.SETTING_DETAIL_SUCCESS);
    }


    @PatchMapping
    @Operation(summary = "설정 수정 API", description = "사용자가 서비스 내에서의 설정을 수정하는 API 입니다.")
    public APIResponse<Void> update(
            @AuthenticationPrincipal CustomUserDetails userDetail,
            @RequestBody MemberSettingUpdateReqDTO request
    ) {
        memberSettingService.update(userDetail.getMember().getId(), request);
        return APIResponse.onSuccess(null, SettingSuccessCode.SETTING_UPDATE_SUCCESS);
    }


}
