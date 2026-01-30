package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.alarm.entity.MissionHistory;
import Lumo.lumo_backend.domain.member.dto.MissionStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MissionHistoryRepository extends JpaRepository<MissionHistory, Long> {

    List<MissionHistory> findByAlarmOrderByCompletedAtDesc(Alarm alarm);

    List<MissionHistory> findByAlarm_Member_IdOrderByCompletedAtDesc(Long memberId);


    @Query("SELECT mh from MissionHistory mh join Alarm a ON mh.alarm.alarmId = a.alarmId where a.member.id = :memberId")
    List<MissionHistory> findAllByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT COUNT(mh) as total, " +
            "SUM(CASE WHEN mh.isSuccess = true THEN 1 ELSE 0 END) as success " +
            "FROM MissionHistory mh " +
            "WHERE mh.alarm.member.id = :memberId " +
            "AND mh.completedAt >= :startDate")
    MissionStat findMissionStatsByMember(@Param("memberId") Long memberId, @Param("startDate") LocalDateTime startDate);
}