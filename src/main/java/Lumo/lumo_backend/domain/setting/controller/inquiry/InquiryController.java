package Lumo.lumo_backend.domain.setting.controller.inquiry;

import Lumo.lumo_backend.domain.setting.dto.InquiryCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.InquiryResponseDTO;
import Lumo.lumo_backend.domain.setting.service.InquiryService;
import Lumo.lumo_backend.domain.setting.status.InquirySuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "사용자 문의 사항 생성")
    @PostMapping
    public APIResponse<InquiryResponseDTO> create(
            @RequestHeader Long memberId, //todo jwt 설정 이후 수정 필요
            @RequestBody @Valid InquiryCreateRequestDTO inquiryCreateRequestDTO
    ) {
        InquiryResponseDTO inquiryResponseDTO = inquiryService.create(memberId, inquiryCreateRequestDTO);
        return APIResponse.onSuccess(inquiryResponseDTO, InquirySuccessCode.INQUIRY_CREATE_SUCCESS);
    }

    @Operation(summary = "사용자 문의사항 수정")
    @PatchMapping("/{inquiryId}")
    public APIResponse<Object> update(
            @RequestHeader Long memberId, //todo jwt 설정 이후 수정 필요
            @PathVariable Long inquiryId,
            @RequestBody @Valid InquiryCreateRequestDTO inquiryCreateRequestDTO
    ) {
        InquiryResponseDTO inquiryResponseDTO = inquiryService.update(memberId, inquiryId, inquiryCreateRequestDTO);
        return APIResponse.onSuccess(inquiryResponseDTO, InquirySuccessCode.INQUIRY_UPDATE_SUCCESS);
    }

    @GetMapping("/{inquiryId}")
    public APIResponse<Object> get() {
        return null;
    }

}
