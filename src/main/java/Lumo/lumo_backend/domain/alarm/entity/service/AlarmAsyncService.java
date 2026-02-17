package Lumo.lumo_backend.domain.alarm.entity.service;

import Lumo.lumo_backend.domain.alarm.entity.MissionHistory;
import Lumo.lumo_backend.domain.alarm.entity.repository.AlarmLogRepository;
import Lumo.lumo_backend.domain.alarm.entity.repository.MissionHistoryRepository;
import Lumo.lumo_backend.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 비동기 처리 전용 서비스
 * -업데이트 등 응답 속도에 영향을 주지 않아야 하는 작업 처리
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmAsyncService {

    private final MissionHistoryRepository missionHistoryRepository;
    private final AlarmLogRepository alarmLogRepository;

    /**
     *비동기 업데이트
     */
    @Async("alarmTaskExecutor")
    @Transactional
    public void updateMemberStatisticsAsync(Member member) {
        try {
            log.debug("비동기 업데이트 시작 - memberId: {}", member.getId());

            LocalDateTime monthStart = LocalDateTime.now()
                    .withDayOfMonth(1)
                    .withHour(0).withMinute(0).withSecond(0).withNano(0);

            int totalAttempts = (int) missionHistoryRepository
                    .findByAlarm_Member_IdOrderByCompletedAtDesc(member.getId())
                    .stream()
                    .filter(mh -> mh.getCompletedAt().isAfter(monthStart))
                    .count();

            int totalSuccess = (int) missionHistoryRepository
                    .findByAlarm_Member_IdOrderByCompletedAtDesc(member.getId())
                    .stream()
                    .filter(mh -> mh.getCompletedAt().isAfter(monthStart) && mh.getIsSuccess())
                    .count();

            LocalDateTime todayStart = LocalDateTime.now()
                    .withHour(0).withMinute(0).withSecond(0).withNano(0);

            boolean todaySuccess = missionHistoryRepository
                    .findByAlarm_Member_IdOrderByCompletedAtDesc(member.getId())
                    .stream()
                    .anyMatch(mh -> mh.getCompletedAt().isAfter(todayStart) && mh.getIsSuccess());

            if (todaySuccess) {
                member.incrementConsecutiveSuccessCnt();
            }

            member.updateMissionSuccessRate(totalSuccess, totalAttempts);

            log.debug("비동기업데이트 완료 - memberId: {}, 시도: {}, 성공: {}",
                    member.getId(), totalAttempts, totalSuccess);

        } catch (Exception e) {
            log.error("비동기업데이트 실패 - memberId: {}", member.getId(), e);
        }
    }
}
