package Lumo.lumo_backend.domain.setting.controller.terms;


import Lumo.lumo_backend.global.apiResponse.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

//@RestController
@Slf4j
@RequestMapping("/api/admin/terms/{termId}/version")
public class AdminTermsVersionController {

    @GetMapping
    public APIResponse<Object> getTermsAgreement() {
        return null;
    }

    @PostMapping
    public APIResponse<Object> createTermsVersion() {
        return null;
    }

    @PatchMapping("/{termsVersionId}")
    public APIResponse<Object> updateTermsVersion() {
        return null;
    }
}