package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.setting.dto.NoticeCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.NoticeResponseDTO;
import Lumo.lumo_backend.domain.setting.entity.Notice;
import Lumo.lumo_backend.domain.setting.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeResponseDTO create(NoticeCreateRequestDTO noticeCreateRequestDTO) {

        Notice notice = Notice.builder()
                .type(noticeCreateRequestDTO.getType())
                .title(noticeCreateRequestDTO.getTitle())
                .content(noticeCreateRequestDTO.getContent())
                .isActive(true)
                .build();

        Notice saved = noticeRepository.save(notice);
        return NoticeResponseDTO.from(saved);
    }


}
