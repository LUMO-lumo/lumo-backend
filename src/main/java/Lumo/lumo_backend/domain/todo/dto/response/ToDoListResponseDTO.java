package Lumo.lumo_backend.domain.todo.dto.response;

import Lumo.lumo_backend.domain.todo.entity.ToDo;
import java.util.List;
import lombok.Builder;

@Builder
public record ToDoListResponseDTO(
        List<ToDoResponseDTO> toDoList
) {
    public static ToDoListResponseDTO from(List<ToDo> toDos) {
        return ToDoListResponseDTO.builder()
                .toDoList(toDos.stream()
                        .map(ToDoResponseDTO::from)
                        .toList())
                .build();
    }
}
