package Lumo.lumo_backend.domain.encouragement.commandLineRunner;

import Lumo.lumo_backend.domain.encouragement.entity.Encouragement;
import Lumo.lumo_backend.domain.encouragement.repository.EncouragementRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncouragementTextLoader implements CommandLineRunner {

    private final EncouragementRepository encouragementRepository;

    @Getter
    private Encouragement todayEncouragement;

    @Override
    public void run(String... args) throws IOException {
        if (encouragementRepository.count() > 0) {
            loadTodayEncouragement();
            return;
        }

        ClassPathResource resource = new ClassPathResource("encouragements.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        reader.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .forEach(line -> {
                    try {
                        encouragementRepository.save(Encouragement.builder()
                                .content(line)
                                .build());
                    } catch (DataIntegrityViolationException ignore) {

                    }
                });

        loadTodayEncouragement();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void loadTodayEncouragement() {
        todayEncouragement =encouragementRepository.findRandomOne();
        log.info("Today encouragement is {}", todayEncouragement.getContent());
    }
}

