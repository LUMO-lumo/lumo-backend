package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.alarm.entity.MissionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionHistoryRepository extends JpaRepository<MissionHistory, Long> {

    List<MissionHistory> findByAlarmOrderByCompletedAtDesc(Alarm alarm);

    List<MissionHistory> findByAlarm_Member_IdOrderByCompletedAtDesc(Long memberId);


    @Query("SELECT mh from MissionHistory mh join Alarm a ON mh.alarm.alarmId = a.alarmId where a.member.id = :memberId")
    List<MissionHistory> findAllByMemberId(@Param("memberId") Long memberId);
}