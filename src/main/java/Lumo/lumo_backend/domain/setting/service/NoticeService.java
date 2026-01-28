package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.setting.dto.NoticeCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.NoticePreviewDTO;
import Lumo.lumo_backend.domain.setting.dto.NoticeResponseDTO;
import Lumo.lumo_backend.domain.setting.entity.Notice;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.domain.setting.repository.NoticeRepository;
import Lumo.lumo_backend.domain.setting.status.SettingErrorCode;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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
                .active(true)
                .build();

        Notice saved = noticeRepository.save(notice);
        return NoticeResponseDTO.from(saved);
    }


    public NoticeResponseDTO update(Long noticeId, NoticeCreateRequestDTO noticeCreateRequestDTO) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new SettingException(SettingErrorCode.NOTICE_NOT_FOUND));

        notice.update(
                noticeCreateRequestDTO.getType(),
                noticeCreateRequestDTO.getTitle(),
                noticeCreateRequestDTO.getContent()
        );

        noticeRepository.flush(); //수정일자 적용된 값 반환 위함
        return NoticeResponseDTO.from(notice);
    }

    public void softDelete(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new SettingException(SettingErrorCode.NOTICE_NOT_FOUND));

        notice.softDelete();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void hardDelete() {
        List<Notice> deleted = noticeRepository.findByDeletedAtBefore(LocalDateTime.now().minusMonths(1));
        noticeRepository.deleteAll(deleted);
    }

    @Transactional(readOnly = true)
    public NoticeResponseDTO get(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new SettingException(SettingErrorCode.NOTICE_NOT_FOUND));

        if (!notice.isActive()) {
            throw new SettingException(SettingErrorCode.NOTICE_NOT_FOUND);
        }

        return NoticeResponseDTO.from(notice);
    }

    @Transactional(readOnly = true)
    public List<NoticePreviewDTO> getList(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return noticeRepository.findAllByActiveOrderByCreatedAtDesc(true);
        } else {
            return noticeRepository.search(keyword);
        }
    }
}
