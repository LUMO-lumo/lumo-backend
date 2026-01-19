package Lumo.lumo_backend.domain.setting.controller;


import Lumo.lumo_backend.domain.setting.dto.DeviceCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.service.MemberDeviceService;
import Lumo.lumo_backend.domain.setting.status.SettingSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.security.userDetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 기기 컨트롤러
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/devices")
@Tag(name = "기기 API", description = "사용자 기기 관련 API 입니다.")
public class DeviceController {

    private final MemberDeviceService memberDeviceService;

    @PostMapping
    @Operation(summary = "기기 생성 API", description = "사용자의 기기를 DB에 등록합니다.")
    public APIResponse<Void> create(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody DeviceCreateRequestDTO request
    ) {

        memberDeviceService.create(userDetails.getMember().getId(), request);
        return APIResponse.onSuccess(null, SettingSuccessCode.DEVICE_CREATE_SUCCESS);
    }

    @GetMapping
    public APIResponse<Object> getMemberDevice() {
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
