package Lumo.lumo_backend.domain.todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoCreateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoUpdateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoListResponseDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import Lumo.lumo_backend.domain.todo.exception.ToDoException;
import Lumo.lumo_backend.domain.todo.repository.ToDoRepository;
import Lumo.lumo_backend.domain.todo.status.ToDoErrorCode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ToDoServiceTest {

    @Autowired
    ToDoService toDoService;
    @Autowired
    ToDoRepository toDoRepository;

    @Test
    @DisplayName("할 일 생성")
    void create() {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO requestDTO = new ToDoCreateRequestDTO(eventDate, content);

        ToDoResponseDTO responseDTO = toDoService.create(memberId, requestDTO);

        ToDo toDo = toDoRepository.findById(responseDTO.id()).orElseThrow(RuntimeException::new);
        assertNotNull(responseDTO.id());
        assertEquals(memberId, toDo.getMember().getId());
        assertEquals(requestDTO.content(), responseDTO.content());
        assertEquals(requestDTO.eventDate(), responseDTO.eventDate());
    }

    @Test
    @DisplayName("할 일 생성-사용자 NOT FOUND")
    void create_MemberNotFound() {
        Long memberId = 0L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO requestDTO = new ToDoCreateRequestDTO(eventDate, content);

        assertThrows(MemberException.class, () -> toDoService.create(memberId, requestDTO));
    }

    @Test
    @DisplayName("할 일 수정")
    void update() {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO(eventDate, content);
        ToDoResponseDTO created = toDoService.create(memberId, toDoCreateRequestDTO);

        String newContent = "분리수거하기";
        ToDoUpdateRequestDTO toDoUpdateRequestDTO = new ToDoUpdateRequestDTO(newContent);
        ToDoResponseDTO updated = toDoService.update(memberId, created.id(), toDoUpdateRequestDTO);

        assertEquals(created.id(), updated.id());
        assertEquals(newContent, updated.content());
        assertEquals(created.eventDate(), updated.eventDate());
    }

    @Test
    @DisplayName("할 일 수정-할 일 NOT FOUND")
    void update_ToDoNOTFOUND() {
        Long memberId = 1L;
        String newContent = "분리수거하기";
        ToDoUpdateRequestDTO toDoUpdateRequestDTO = new ToDoUpdateRequestDTO(newContent);

        assertThrows(ToDoException.class, () -> toDoService.update(memberId, 0L, toDoUpdateRequestDTO));
    }

    @Test
    @DisplayName("할 일 수정-ACCESS_DENIED")
    void update_ACCESS_DENIED() {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO(eventDate, content);
        ToDoResponseDTO created = toDoService.create(memberId, toDoCreateRequestDTO);

        String newContent = "분리수거하기";
        ToDoUpdateRequestDTO toDoUpdateRequestDTO = new ToDoUpdateRequestDTO(newContent);

        Throwable throwable = catchThrowable(() -> toDoService.update(2L, created.id(), toDoUpdateRequestDTO));

        assertThat(throwable).isInstanceOf(ToDoException.class)
                .hasMessage(ToDoErrorCode.ACCESS_DENIED.getMessage());
    }

    @Test
    @DisplayName("할 일 삭제")
    void delete() {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO(eventDate, content);
        ToDoResponseDTO created = toDoService.create(memberId, toDoCreateRequestDTO);

        toDoService.delete(memberId, created.id());

        Optional<ToDo> toDo = toDoRepository.findById(created.id());
        assertThat(toDo).isEmpty();
    }

    @Test
    @DisplayName("할 일 삭제 TODO NOT FOUND")
    void deleteToDoNOTFOUND() {
        Throwable throwable = catchThrowable(() -> toDoService.delete(1L, 0L));

        assertThat(throwable).isInstanceOf(ToDoException.class)
                .hasMessage(ToDoErrorCode.NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("할 일 삭제 ACCESS DENIED")
    void delete_AccessDenied() {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO(eventDate, content);
        ToDoResponseDTO created = toDoService.create(memberId, toDoCreateRequestDTO);

        Throwable throwable = catchThrowable(() -> toDoService.delete(2L, created.id()));
        assertThat(throwable).isInstanceOf(ToDoException.class)
                .hasMessage(ToDoErrorCode.ACCESS_DENIED.getMessage());
    }

    @Test
    @DisplayName("일별 할 일 조회")
    void findToDoListByEventDate() {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO toDoCreateRequestDTO = new ToDoCreateRequestDTO(eventDate, content);
        ToDoResponseDTO created = toDoService.create(memberId, toDoCreateRequestDTO);

        String content2 = "회의";

        ToDoCreateRequestDTO toDoCreateRequestDTO2 = new ToDoCreateRequestDTO(eventDate, content2);
        ToDoResponseDTO created2 = toDoService.create(memberId, toDoCreateRequestDTO2);

        ToDoListResponseDTO toDoListResponseDTO = toDoService.findToDoListByEventDate(memberId, eventDate);
        List<ToDoResponseDTO> toDoDTOList = toDoListResponseDTO.toDoList();
        assertEquals(2,toDoDTOList.size());
        for (ToDoResponseDTO toDoResponseDTO : toDoDTOList) {
            assertEquals(eventDate, toDoResponseDTO.eventDate());
        }
    }
}