package Lumo.lumo_backend.domain.routine.repository;

import Lumo.lumo_backend.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findAllById(Long id);

    Optional<Routine> findByIdAndMember_Id(Long id, Long memberId);
}
