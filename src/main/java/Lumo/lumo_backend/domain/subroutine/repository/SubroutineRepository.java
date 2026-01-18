package Lumo.lumo_backend.domain.subroutine.repository;

import Lumo.lumo_backend.domain.subroutine.entity.Subroutine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubroutineRepository extends JpaRepository<Subroutine, Long> {
}
