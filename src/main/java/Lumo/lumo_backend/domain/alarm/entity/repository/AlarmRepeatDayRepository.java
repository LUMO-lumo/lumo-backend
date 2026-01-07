package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.alarm.entity.AlarmRepeatDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepeatDayRepository extends JpaRepository<AlarmRepeatDay, Long> {

    List<AlarmRepeatDay> findByAlarm(Alarm alarm);

    void deleteByAlarm(Alarm alarm);
}