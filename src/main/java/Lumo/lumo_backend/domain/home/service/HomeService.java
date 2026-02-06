package Lumo.lumo_backend.domain.home.service;

import Lumo.lumo_backend.domain.encouragement.commandLineRunner.EncouragementTextLoader;
import Lumo.lumo_backend.domain.encouragement.entity.Encouragement;
import Lumo.lumo_backend.domain.home.dto.HomeResponseDTO;
import Lumo.lumo_backend.domain.member.dto.MemberRespDTO.GetMissionRecordRespDTO;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.service.MemberService;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.todo.service.ToDoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ToDoService toDoService;
    private final EncouragementTextLoader encouragementTextLoader;

    public HomeResponseDTO get(Member member) {
        Member persistedMember = getPersistedMember(member);

        Encouragement encouragement = encouragementTextLoader.getTodayEncouragement();
        List<String> todo = toDoService.findTodayThreeToDo(persistedMember);
        GetMissionRecordRespDTO missionRecord = memberService.getMissionRecord(persistedMember);

        return HomeResponseDTO.builder()
                .encouragement(encouragement.getContent())
                .todo(todo)
                .missionRecord(missionRecord)
                .build();
    }

    private Member getPersistedMember(Member member) {
        return memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
    }
}
