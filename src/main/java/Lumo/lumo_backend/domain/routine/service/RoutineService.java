package Lumo.lumo_backend.domain.routine.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.routine.dto.RoutineRequestDTO;
import Lumo.lumo_backend.domain.routine.entity.Routine;
import Lumo.lumo_backend.domain.routine.repository.RoutineRepository;
import Lumo.lumo_backend.global.apiResponse.status.ErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public RoutineRequestDTO.GetRoutineReqDTO getRoutine (Long memberId){
        return null;
    }


    public void deleteRoutine(Long routineId){
        return;
    }

    public void renameRoutine( Long routineId,  String title) {
        return;
    }

}
