package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.alarm.entity.AlarmLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmLogRepository extends JpaRepository<AlarmLog, Long> {

    List<AlarmLog> findByAlarm_Member_MemberIdOrderByTriggeredAtDesc(Long memberId);

    List<AlarmLog> findByAlarmOrderByTriggeredAtDesc(Alarm alarm);
}