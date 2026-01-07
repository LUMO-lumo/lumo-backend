package Lumo.lumo_backend.domain.member.service;

import Lumo.lumo_backend.domain.member.dto.MemberRespDTO;
import Lumo.lumo_backend.domain.member.entity.Login;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberRespDTO.CheckDuplicateRespDTO checkEmailDuplicate (String email){
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        if (byEmail.isPresent()){
            return MemberRespDTO.CheckDuplicateRespDTO.builder().login(byEmail.get().getLogin()).build();
        }
        else{
            return MemberRespDTO.CheckDuplicateRespDTO.builder().login(Login.NULL).build();
        }
    }


}
