package Lumo.lumo_backend.domain.todo.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoCreateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoUpdateRequestDTO;
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

    public ToDoResponseDTO create(Member member, ToDoCreateRequestDTO toDoCreateRequestDTO) {
        Member persistedMember = getPersistedMember(member);

        ToDo toDo = ToDo.builder()
                .member(persistedMember)
                .eventDate(toDoCreateRequestDTO.eventDate())
                .content(toDoCreateRequestDTO.content())
                .build();

        ToDo savedToDo = toDoRepository.save(toDo);
        return ToDoResponseDTO.from(savedToDo);
    }

    public ToDoResponseDTO update(Member member, Long toDoId, ToDoUpdateRequestDTO toDoUpdateRequestDTO) {
        Member persistedMember = getPersistedMember(member);

        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new ToDoException(ToDoErrorCode.NOT_FOUND));

        if (toDo.getMember()!=persistedMember) {
            throw new ToDoException(ToDoErrorCode.ACCESS_DENIED);
        }

        String content = toDoUpdateRequestDTO.content();
        toDo.update(content);

        return ToDoResponseDTO.from(toDo);
    }

    public void delete(Member member, Long toDoId) {
        Member persistedMember = getPersistedMember(member);

        ToDo toDo = toDoRepository.findById(toDoId)
                .orElseThrow(() -> new ToDoException(ToDoErrorCode.NOT_FOUND));

        if (toDo.getMember()!=persistedMember) {
            throw new ToDoException(ToDoErrorCode.ACCESS_DENIED);
        }

        toDoRepository.delete(toDo); //hard delete
    }

    @Transactional(readOnly = true)
    public List<ToDoResponseDTO> findToDoListByEventDate(Member member, LocalDate eventDate) {
        Member persistedMember = getPersistedMember(member);

        List<ToDoResponseDTO> toDos = toDoRepository.findAllByMemberAndEventDate(persistedMember, eventDate);

        return toDos;
    }

    private Member getPersistedMember(Member member) {
        return memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
    }
}
