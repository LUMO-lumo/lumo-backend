package Lumo.lumo_backend.domain.encouragement.commandLineRunner;

import Lumo.lumo_backend.domain.encouragement.entity.Encouragement;
import Lumo.lumo_backend.domain.encouragement.repository.EncouragementRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EncouragementTextLoader implements CommandLineRunner {

    private final EncouragementRepository repository;

    @Override
    public void run(String... args) throws IOException {
        if (repository.count() > 0) {
            return;
        }

        ClassPathResource resource = new ClassPathResource("encouragements.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));

        reader.lines()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .forEach(line -> {
                    try {
                        repository.save(Encouragement.builder()
                                .content(line)
                                .build());
                    } catch (DataIntegrityViolationException ignore) {

                    }
                });
    }

}

