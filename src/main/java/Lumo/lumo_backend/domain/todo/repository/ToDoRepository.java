package Lumo.lumo_backend.domain.todo.repository;

import Lumo.lumo_backend.domain.todo.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
