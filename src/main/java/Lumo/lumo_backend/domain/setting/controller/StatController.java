package Lumo.lumo_backend.domain.setting.controller;


import Lumo.lumo_backend.domain.setting.dto.MemberStatResDTO;
import Lumo.lumo_backend.domain.setting.service.MemberStatService;
import Lumo.lumo_backend.domain.setting.status.SettingSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.security.userDetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatController {

    private final MemberStatService memberStatService;

    @GetMapping
    public APIResponse<MemberStatResDTO> get(
            @AuthenticationPrincipal CustomUserDetails userDetail
    ) {

        return APIResponse.onSuccess(memberStatService.get(userDetail.getMember().getId()), SettingSuccessCode.STAT_DETAIL_SUCCESS);
    }

}
