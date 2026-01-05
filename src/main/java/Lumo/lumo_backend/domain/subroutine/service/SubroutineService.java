package Lumo.lumo_backend.domain.subroutine.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.routine.entity.Routine;
import Lumo.lumo_backend.domain.routine.exception.RoutineException;
import Lumo.lumo_backend.domain.routine.repository.RoutineRepository;
import Lumo.lumo_backend.domain.routine.status.RoutineErrorCode;
import Lumo.lumo_backend.domain.subroutine.entity.Subroutine;
import Lumo.lumo_backend.domain.subroutine.repository.SubroutineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubroutineService {

    private final RoutineRepository routineRepository;
    private final MemberRepository memberRepository;
    private final SubroutineRepository subroutineRepository;

    @Transactional
    public Long createSubroutine (Member member, Long routineId, String title) {
        Member member1 = memberRepository.findById(member.getId()).orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
        Routine routine = routineRepository.findById(routineId).orElseThrow(() -> new RoutineException(RoutineErrorCode.ROUTINE_NOT_FOUND));
        Subroutine savedSubroutine = subroutineRepository.save(new Subroutine(title));
        routine.addSubroutine(savedSubroutine);

        return savedSubroutine.getId();
    }
}
