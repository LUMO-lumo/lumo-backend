package Lumo.lumo_backend.global.init;

import Lumo.lumo_backend.domain.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkerInitializer implements ApplicationRunner {
    private final EmailService emailService;

    @Override
    public void run(ApplicationArguments args) {
        // 서버가 켜지면 15개의 비동기 워커 스레드를 생성합니다.
        for (int i = 0; i < 15; i++) {
            emailService.startMailWorker(i);
        }
    }
}