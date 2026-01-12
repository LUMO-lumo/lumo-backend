package Lumo.lumo_backend.domain.encouragement.repository;

import Lumo.lumo_backend.domain.encouragement.entity.Encouragement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EncouragementRepository extends JpaRepository<Encouragement, Long> {
    @Query(value = "SELECT * FROM encouragement ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Encouragement findRandomOne();
}
