package Lumo.lumo_backend.global.init;

import Lumo.lumo_backend.domain.member.entity.memberEnum.Login;
import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.entity.memberEnum.MemberRole;
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
        String TEST_EMAIL = "test@test.com";
        String TEST_USERNAME = "testuser";
        String TEST_PASSWORD = "password";
        Login TEST_LOGIN = Login.NORMAL;
        MemberRole TEST_ROLE = MemberRole.USER;

        if (memberRepository.existsByEmail(TEST_EMAIL)) return;

//        Member member = Member.builder()
//                .login(Login.NORMAL)
//                .email("test@test.com")
//                .username("testuser")
//                .password("password")
//                .isProUpgraded(false)
//                // missionSuccessRate, consecutiveSuccessCnt는 엔티티 기본값 사용
//                .build();


        Member member = Member.create(TEST_EMAIL, TEST_USERNAME, TEST_PASSWORD, TEST_LOGIN, TEST_ROLE);
        memberRepository.save(member);

        memberRepository.save(member);
    }
}