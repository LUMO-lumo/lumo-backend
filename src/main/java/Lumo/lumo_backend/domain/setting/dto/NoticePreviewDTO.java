package Lumo.lumo_backend.domain.setting.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class NoticePreviewDTO{
    private Long id;
    private String type;
    private String title;
    private LocalDateTime createdAt;
}