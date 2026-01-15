package Lumo.lumo_backend.domain.setting.dto;

import Lumo.lumo_backend.domain.setting.entity.Inquiry;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InquiryResponseDTO {

    @Schema(description = "문의 아이디", example = "1")
    private Long id;

    @Schema(description = "제목", example = "알람 관련 문의")
    private String title;

    @Schema(description = "내용", example = "알람이 안 꺼져요")
    private String content;

    @Schema(description = "생성일", example = "2026-01-13")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2026-01-13")
    private LocalDateTime updatedAt;

//    private Inquiry childInquiry;

    public static InquiryResponseDTO from(Inquiry inquiry) {
        return new InquiryResponseDTO(
                inquiry.getId(),
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getCreatedAt(),
                inquiry.getUpdatedAt()
        );
    }
}
