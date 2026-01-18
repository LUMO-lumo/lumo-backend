package Lumo.lumo_backend.domain.setting.controller.inquiry;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.setting.dto.InquiryCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.InquiryResponseDTO;
import Lumo.lumo_backend.domain.setting.service.InquiryService;
import Lumo.lumo_backend.domain.setting.status.InquirySuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.security.userDetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/inquiries")
@Tag(name = "문의사항")
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "사용자 문의사항 생성")
    @PostMapping
    public APIResponse<InquiryResponseDTO> create(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid InquiryCreateRequestDTO inquiryCreateRequestDTO
    ) {
        InquiryResponseDTO inquiryResponseDTO = inquiryService.create(customUserDetails.getMember(), inquiryCreateRequestDTO);
        return APIResponse.onSuccess(inquiryResponseDTO, InquirySuccessCode.INQUIRY_CREATE_SUCCESS);
    }

    @Operation(summary = "사용자 문의사항 수정")
    @PatchMapping("/{inquiryId}")
    public APIResponse<Object> update(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long inquiryId,
            @RequestBody @Valid InquiryCreateRequestDTO inquiryCreateRequestDTO
    ) {
        InquiryResponseDTO inquiryResponseDTO = inquiryService.update(customUserDetails.getMember(), inquiryId, inquiryCreateRequestDTO);
        return APIResponse.onSuccess(inquiryResponseDTO, InquirySuccessCode.INQUIRY_UPDATE_SUCCESS);
    }

    @GetMapping("/{inquiryId}")
    public APIResponse<Object> get() {
        return null;
    }

}
