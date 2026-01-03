package Lumo.lumo_backend.domain.todo.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.exception.code.MemberErrorCode;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.todo.dto.request.CreateToDoRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import Lumo.lumo_backend.domain.todo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ToDoResponseDTO create(Long memberId, CreateToDoRequestDTO createToDoRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        ToDo toDo = ToDo.builder()
                .member(member)
                .eventDate(createToDoRequestDTO.eventDate())
                .content(createToDoRequestDTO.content())
                .build();

        ToDo savedToDo = toDoRepository.save(toDo);
        return ToDoResponseDTO.from(savedToDo);
    }
}
