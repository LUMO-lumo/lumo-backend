package Lumo.lumo_backend.domain.routine.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.routine.dto.RoutineRespDTO;
import Lumo.lumo_backend.domain.routine.entity.Routine;
import Lumo.lumo_backend.domain.routine.repository.RoutineRepository;
import Lumo.lumo_backend.domain.subroutine.converter.SubroutineConverter;
import Lumo.lumo_backend.global.apiResponse.status.ErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoutineService {

    private final MemberRepository memberRepository;
    private final RoutineRepository routineRepository;

    @Transactional
    public void createRoutine (Long memberId, String title){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new GeneralException(ErrorCode.MEMBER_TEST_EXCEPTION));
        Routine routine = new Routine(title, member);
        routineRepository.save(routine);
    }

    public List<RoutineRespDTO.GetRoutineDTO> getRoutine (Long memberId){

        List<Routine> routineList = routineRepository.findAllById(memberId);

        return routineList.stream()
                .map(routine ->
                    RoutineRespDTO.GetRoutineDTO.builder()
                            .routineTitle(routine.getTitle())
                            .subroutineList(SubroutineConverter.toSubroutineDTO(routine))
                            .build()
                )
                .toList();
    }


    public void deleteRoutine(Long routineId){
        return;
    }

    public void renameRoutine( Long routineId,  String title) {
        return;
    }

}
