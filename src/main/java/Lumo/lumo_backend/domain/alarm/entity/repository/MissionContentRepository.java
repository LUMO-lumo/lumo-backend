package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionContent;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MissionContentRepository extends JpaRepository<MissionContent, Long> {

    @Query(value = "SELECT * FROM mission_content WHERE mission_type = :type AND difficulty = :difficulty ORDER BY RAND() LIMIT :count", nativeQuery = true)
    List<MissionContent> findRandomByTypeAndDifficulty(
            @Param("type") String type,
            @Param("difficulty") String difficulty,
            @Param("count") int count
    );

    List<MissionContent> findByMissionTypeAndDifficulty(MissionType missionType, Difficulty difficulty);
}