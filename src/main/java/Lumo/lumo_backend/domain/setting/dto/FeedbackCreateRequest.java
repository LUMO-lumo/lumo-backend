package Lumo.lumo_backend.domain.setting.dto;

import lombok.Getter;

@Getter
public class FeedbackCreateRequest {
    private String title;
    private String content;
}