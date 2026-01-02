package Lumo.lumo_backend.domain.member.repository;

import Lumo.lumo_backend.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Long, Member> {
}
