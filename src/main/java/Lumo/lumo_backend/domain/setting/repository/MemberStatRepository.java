package Lumo.lumo_backend.domain.setting.repository;

import Lumo.lumo_backend.domain.setting.entity.MemberStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberStatRepository extends JpaRepository<MemberStat, Long> {

}
