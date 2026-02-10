package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.AlarmMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// AlarmMissionRepository.java
@Repository
public interface AlarmMissionRepository extends JpaRepository<AlarmMission, Long> {
}