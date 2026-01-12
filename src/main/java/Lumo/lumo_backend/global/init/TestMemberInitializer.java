package Lumo.lumo_backend.global.init;

import Lumo.lumo_backend.domain.member.entity.Login;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.*;
import lombok.*;

@Component
@RequiredArgsConstructor
@Profile("local")
public class TestMemberInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;

    @Override
    public void run(String... args) {
        if (memberRepository.existsByEmail("test@test.com")) return;

        Member member = Member.builder()
                .login(Login.NORMAL)
                .email("test@test.com")
                .username("testuser")
                .password("password")
                .isProUpgraded(false)
                // missionSuccessRate, consecutiveSuccessCnt는 엔티티 기본값 사용
                .build();

        memberRepository.save(member);
    }
}