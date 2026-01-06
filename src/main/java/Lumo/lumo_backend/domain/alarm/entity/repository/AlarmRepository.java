package Lumo.lumo_backend.domain.alarm.entity.repository;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    @Query("SELECT a FROM Alarm a " +
            "LEFT JOIN FETCH a.repeatDays " +
            "LEFT JOIN FETCH a.alarmSnooze " +
            "LEFT JOIN FETCH a.alarmMission " +
            "WHERE a.member = :member")
    List<Alarm> findAllByMemberWithDetails(@Param("member") Member member);

    @Query("SELECT a FROM Alarm a " +
            "LEFT JOIN FETCH a.repeatDays " +
            "LEFT JOIN FETCH a.alarmSnooze " +
            "LEFT JOIN FETCH a.alarmMission " +
            "WHERE a.alarmId = :alarmId AND a.member = :member")
    Optional<Alarm> findByIdAndMemberWithDetails(
            @Param("alarmId") Long alarmId,
            @Param("member") Member member
    );

    boolean existsByAlarmIdAndMember(Long alarmId, Member member);
}