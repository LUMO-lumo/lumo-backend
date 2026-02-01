package Lumo.lumo_backend.domain.todo.repository;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDoResponseDTO> findAllByMemberAndEventDate(Member member, LocalDate eventDate);

    @Query(value = "SELECT content FROM to_do WHERE member_id=:memberId AND event_date=:eventDate LIMIT 3", nativeQuery = true)
    List<String> findTodayThreeToDo(Long memberId, LocalDate eventDate);

    @Query(value = "SELECT t.content FROM ToDo t WHERE t.member=:member AND t.eventDate=:eventDate")
    List<String> findContentByMemberAndEventDate(Member member, LocalDate eventDate);
}
