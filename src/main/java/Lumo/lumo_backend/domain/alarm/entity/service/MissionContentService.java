package Lumo.lumo_backend.domain.alarm.entity.service;

import Lumo.lumo_backend.domain.alarm.entity.MissionContent;
import Lumo.lumo_backend.domain.alarm.entity.dto.MissionContentCreateDto;
import Lumo.lumo_backend.domain.alarm.entity.dto.MissionContentResponseDto;
import Lumo.lumo_backend.domain.alarm.entity.exception.AlarmException;
import Lumo.lumo_backend.domain.alarm.entity.exception.code.AlarmErrorCode;
import Lumo.lumo_backend.domain.alarm.entity.repository.MissionContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionContentService {

    private final MissionContentRepository missionContentRepository;

    /**
     * 미션 문제 등록
     */
    @Transactional
    public MissionContentResponseDto createMissionContent(MissionContentCreateDto requestDto) {
        MissionContent content = MissionContent.builder()
                .missionType(requestDto.getMissionType())
                .difficulty(requestDto.getDifficulty())
                .question(requestDto.getQuestion())
                .answer(requestDto.getAnswer())
                .build();

        MissionContent savedContent = missionContentRepository.save(content);
        return MissionContentResponseDto.from(savedContent);
    }

    /**
     * 미션 문제 삭제
     */
    @Transactional
    public void deleteMissionContent(Long contentId) {
        MissionContent content = missionContentRepository.findById(contentId)
                .orElseThrow(() -> new AlarmException(AlarmErrorCode.MISSION_CONTENT_NOT_FOUND));
        missionContentRepository.delete(content);
    }
}