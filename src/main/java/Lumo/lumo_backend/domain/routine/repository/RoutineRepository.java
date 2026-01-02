package Lumo.lumo_backend.domain.routine.repository;

import Lumo.lumo_backend.domain.routine.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Long, Routine> {

}
