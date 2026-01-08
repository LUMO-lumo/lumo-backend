package Lumo.lumo_backend.domain.todo.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final MemberRepository memberRepository;

    public ToDoResponseDTO create(Long memberId, ToDoCreateRequestDTO toDoCreateRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));

        ToDo toDo = ToDo.builder()
                .member(member)
                .eventDate(toDoCreateRequestDTO.eventDate())
                .content(toDoCreateRequestDTO.content())
                .build();

        ToDo savedToDo = toDoRepository.save(toDo);
        return ToDoResponseDTO.from(savedToDo);
    }

    public ToDoResponseDTO update(Long memberId, Long toDoId, ToDoUpdateRequestDTO toDoUpdateRequestDTO) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new ToDoException(ToDoErrorCode.NOT_FOUND));

        if (!toDo.isOwnedBy(memberId)) {
            throw new ToDoException(ToDoErrorCode.ACCESS_DENIED);
        }

        String content = toDoUpdateRequestDTO.content();
        toDo.update(content);

        return ToDoResponseDTO.from(toDo);
    }

    public void delete(Long memberId, Long toDoId) {
        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new ToDoException(ToDoErrorCode.NOT_FOUND));

        if (!toDo.isOwnedBy(memberId)) {
            throw new ToDoException(ToDoErrorCode.ACCESS_DENIED);
        }

        toDoRepository.delete(toDo); //hard delete
    }

    public ToDoListResponseDTO findToDoListByEventDate(Long memberId, LocalDate eventDate) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));

        List<ToDo> toDos = toDoRepository.findAllByMemberAndEventDate(member, eventDate);

        return ToDoListResponseDTO.from(toDos);
    }
}
