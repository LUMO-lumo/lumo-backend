package Lumo.lumo_backend.domain.setting.controller.terms;


import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 약관 동의 컨트롤러
 */
//@RestController
@Slf4j
@RequestMapping("/api/terms/agreements")
public class TermsAgreementController {

    @GetMapping
    public APIResponse<Object> getTermsAgreement() {
        return null;
    }

    @PostMapping
    public APIResponse<Object> createTermsAgreement() {
        return null;
    }

    @PatchMapping("/{agreementId}")
    public APIResponse<Object> updateTermsAgreement() {
        return null;
    }
}
