package Lumo.lumo_backend.domain.setting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class InquiryCreateRequestDTO {

    @Schema(description = "제목", example = "알람 관련 문의")
    @Size(min = 1, max = 100)
    private String title;

    @Schema(description = "내용", example = "알람이 안 꺼져요")
    @Size(min = 1, max = 255)
    private String content;
}
