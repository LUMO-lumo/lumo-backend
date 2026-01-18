package Lumo.lumo_backend.domain.routine.repository;

import Lumo.lumo_backend.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    @EntityGraph(attributePaths = {"subroutineList"})
    List<Routine> findAllByMember_Id(Long memberId);

    Optional<Routine> findByIdAndMember_Id(Long id, Long memberId);
}
