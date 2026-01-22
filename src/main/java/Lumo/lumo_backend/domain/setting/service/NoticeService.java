package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.setting.dto.NoticeCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.NoticeResponseDTO;
import Lumo.lumo_backend.domain.setting.entity.Notice;
import Lumo.lumo_backend.domain.setting.repository.NoticeRepository;
import Lumo.lumo_backend.global.apiResponse.status.ErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;
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


    public NoticeResponseDTO update(Long noticeId, NoticeCreateRequestDTO noticeCreateRequestDTO) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new GeneralException(ErrorCode.BAD_REQUEST));//todo 에러코드 수정

        notice.update(
                noticeCreateRequestDTO.getType(),
                noticeCreateRequestDTO.getTitle(),
                noticeCreateRequestDTO.getContent()
        );

        noticeRepository.flush();
        return NoticeResponseDTO.from(notice);
    }
}
