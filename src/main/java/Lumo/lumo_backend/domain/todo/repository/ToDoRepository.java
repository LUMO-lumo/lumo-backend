package Lumo.lumo_backend.domain.todo.repository;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findAllByMemberAndEventDate(Member member, LocalDate eventDate);
}
