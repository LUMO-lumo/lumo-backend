package Lumo.lumo_backend.domain.alarm.entity;

import Lumo.lumo_backend.domain.alarm.entity.Difficulty;
import Lumo.lumo_backend.domain.alarm.entity.MissionContent;
import Lumo.lumo_backend.domain.alarm.entity.MissionType;
import Lumo.lumo_backend.domain.alarm.entity.repository.MissionContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MissionContentDataLoader {

    private final MissionContentRepository missionContentRepository;

    @Bean
    public CommandLineRunner loadMissionContent() {
        return args -> {
            // 이미 데이터가 있으면 스킵
            if (missionContentRepository.count() > 0) {
                log.info("미션 문제 데이터가 이미 존재합니다. 초기화를 건너뜁니다.");
                return;
            }

            log.info("미션 문제 초기 데이터를 생성합니다...");

            List<MissionContent> contents = new ArrayList<>();

            // MATH - EASY
            contents.addAll(createMathEasy());

            // MATH - MEDIUM
            contents.addAll(createMathMedium());

            // MATH - HARD
            contents.addAll(createMathHard());

            // OX_QUIZ - EASY
            contents.addAll(createOxQuizEasy());

            // OX_QUIZ - MEDIUM
            contents.addAll(createOxQuizMedium());

            // TYPING - EASY
            contents.addAll(createTypingEasy());

            // TYPING - MEDIUM
            contents.addAll(createTypingMedium());

            missionContentRepository.saveAll(contents);

            log.info("미션 문제 {}개 생성 완료!", contents.size());
        };
    }

    private List<MissionContent> createMathEasy() {
        return List.of(
                createContent(MissionType.MATH, Difficulty.EASY, "5 + 3 = ?", "8"),
                createContent(MissionType.MATH, Difficulty.EASY, "10 - 4 = ?", "6"),
                createContent(MissionType.MATH, Difficulty.EASY, "7 + 2 = ?", "9"),
                createContent(MissionType.MATH, Difficulty.EASY, "12 - 5 = ?", "7"),
                createContent(MissionType.MATH, Difficulty.EASY, "8 + 1 = ?", "9"),
                createContent(MissionType.MATH, Difficulty.EASY, "6 + 4 = ?", "10"),
                createContent(MissionType.MATH, Difficulty.EASY, "15 - 8 = ?", "7"),
                createContent(MissionType.MATH, Difficulty.EASY, "9 + 6 = ?", "15"),
                createContent(MissionType.MATH, Difficulty.EASY, "20 - 11 = ?", "9"),
                createContent(MissionType.MATH, Difficulty.EASY, "3 + 7 = ?", "10")
        );
    }

    private List<MissionContent> createMathMedium() {
        return List.of(
                createContent(MissionType.MATH, Difficulty.MEDIUM, "15 + 23 = ?", "38"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "45 - 18 = ?", "27"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "8 × 7 = ?", "56"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "36 ÷ 4 = ?", "9"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "25 + 17 - 9 = ?", "33"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "32 + 19 = ?", "51"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "56 - 28 = ?", "28"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "9 × 6 = ?", "54"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "48 ÷ 6 = ?", "8"),
                createContent(MissionType.MATH, Difficulty.MEDIUM, "18 + 24 - 15 = ?", "27")
        );
    }

    private List<MissionContent> createMathHard() {
        return List.of(
                createContent(MissionType.MATH, Difficulty.HARD, "127 + 358 = ?", "485"),
                createContent(MissionType.MATH, Difficulty.HARD, "234 - 87 = ?", "147"),
                createContent(MissionType.MATH, Difficulty.HARD, "23 × 15 = ?", "345"),
                createContent(MissionType.MATH, Difficulty.HARD, "144 ÷ 12 = ?", "12"),
                createContent(MissionType.MATH, Difficulty.HARD, "89 + 76 - 34 = ?", "131"),
                createContent(MissionType.MATH, Difficulty.HARD, "256 + 189 = ?", "445"),
                createContent(MissionType.MATH, Difficulty.HARD, "512 - 237 = ?", "275"),
                createContent(MissionType.MATH, Difficulty.HARD, "18 × 24 = ?", "432"),
                createContent(MissionType.MATH, Difficulty.HARD, "225 ÷ 15 = ?", "15"),
                createContent(MissionType.MATH, Difficulty.HARD, "156 + 89 - 67 = ?", "178")
        );
    }

    private List<MissionContent> createOxQuizEasy() {
        return List.of(
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "대한민국의 수도는 서울이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "1년은 365일이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "지구는 태양 주위를 돈다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "물은 100도에서 끓는다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "고양이는 포유류다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "하루는 24시간이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "태양은 동쪽에서 뜬다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "김치는 한국 음식이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "한 주는 7일이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.EASY, "겨울은 여름보다 춥다", "O")
        );
    }

    private List<MissionContent> createOxQuizMedium() {
        return List.of(
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "한국의 국화는 무궁화이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "세종대왕은 한글을 만들었다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "광합성은 밤에 일어난다", "X"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "남극은 북극보다 춥다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "박쥐는 새의 한 종류이다", "X"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "독도는 한국 영토이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "거북이는 파충류이다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "고래는 물고기다", "X"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "한반도는 아시아에 있다", "O"),
                createContent(MissionType.OX_QUIZ, Difficulty.MEDIUM, "토마토는 채소이다", "X")
        );
    }

    private List<MissionContent> createTypingEasy() {
        return List.of(
                createContent(MissionType.TYPING, Difficulty.EASY, "안녕하세요", "안녕하세요"),
                createContent(MissionType.TYPING, Difficulty.EASY, "좋은 아침", "좋은 아침"),
                createContent(MissionType.TYPING, Difficulty.EASY, "감사합니다", "감사합니다"),
                createContent(MissionType.TYPING, Difficulty.EASY, "고맙습니다", "고맙습니다"),
                createContent(MissionType.TYPING, Difficulty.EASY, "반갑습니다", "반갑습니다"),
                createContent(MissionType.TYPING, Difficulty.EASY, "잘 지내요", "잘 지내요"),
                createContent(MissionType.TYPING, Difficulty.EASY, "좋은 하루", "좋은 하루"),
                createContent(MissionType.TYPING, Difficulty.EASY, "안녕히 가세요", "안녕히 가세요"),
                createContent(MissionType.TYPING, Difficulty.EASY, "수고하세요", "수고하세요"),
                createContent(MissionType.TYPING, Difficulty.EASY, "환영합니다", "환영합니다")
        );
    }

    private List<MissionContent> createTypingMedium() {
        return List.of(
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "일찍 일어나는 새가 벌레를 잡는다", "일찍 일어나는 새가 벌레를 잡는다"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "시작이 반이다", "시작이 반이다"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "티끌 모아 태산", "티끌 모아 태산"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "백문이 불여일견", "백문이 불여일견"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "천리길도 한 걸음부터", "천리길도 한 걸음부터"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "로마는 하루아침에 이루어지지 않았다", "로마는 하루아침에 이루어지지 않았다"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "급할수록 돌아가라", "급할수록 돌아가라"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "소 잃고 외양간 고친다", "소 잃고 외양간 고친다"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "하늘은 스스로 돕는 자를 돕는다", "하늘은 스스로 돕는 자를 돕는다"),
                createContent(MissionType.TYPING, Difficulty.MEDIUM, "구슬이 서 말이라도 꿰어야 보배", "구슬이 서 말이라도 꿰어야 보배")
        );
    }

    private MissionContent createContent(MissionType type, Difficulty difficulty, String question, String answer) {
        return MissionContent.builder()
                .missionType(type)
                .difficulty(difficulty)
                .question(question)
                .answer(answer)
                .build();
    }
}