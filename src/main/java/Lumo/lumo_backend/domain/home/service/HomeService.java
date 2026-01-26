package Lumo.lumo_backend.domain.home.service;

import Lumo.lumo_backend.domain.encouragement.entity.Encouragement;
import Lumo.lumo_backend.domain.encouragement.repository.EncouragementRepository;
import Lumo.lumo_backend.domain.home.dto.HomeResponseDTO;
import Lumo.lumo_backend.domain.member.dto.MemberRespDTO.GetMissionRecordRespDTO;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.service.MemberService;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.service.ToDoService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberService memberService;
    private final EncouragementRepository encouragementRepository;
    private final ToDoService toDoService;

    public HomeResponseDTO get(Member member) {
        Encouragement encouragement = encouragementRepository.findRandomOne();
        List<ToDoResponseDTO> todo = toDoService.findToDoListByEventDate(member, LocalDate.now());
        GetMissionRecordRespDTO missionRecord = memberService.getMissionRecord(member.getId());

        return HomeResponseDTO.builder()
                .encouragement(encouragement.getContent())
                .todo(todo)
                .missionRecord(missionRecord)
                .build();
    }

}
