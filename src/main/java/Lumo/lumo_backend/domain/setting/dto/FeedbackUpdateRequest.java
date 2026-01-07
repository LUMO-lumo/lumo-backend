package Lumo.lumo_backend.domain.setting.dto;

import lombok.Getter;

@Getter
public class FeedbackUpdateRequest {
    private String title;
    private String content;
}