package Lumo.lumo_backend.domain.routine.repository;

import Lumo.lumo_backend.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutineRepository extends JpaRepository<Routine, Long> {

    List<Routine> findAllById(Long id);
}
