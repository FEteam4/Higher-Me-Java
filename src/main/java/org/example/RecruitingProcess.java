package org.example;

import org.example.io.TextReader;
import org.example.io.TextWriter;
import org.example.coding.CodingTest;
import java.util.Map;

public class RecruitingProcess {

    private final TextWriter textWriter;
    private final User candidate;
    private final CrosswordGame crosswordGame; // 서류 게임
    private final CodingTest codingTest;
    private final QuestionRepository questionRepository;

    public RecruitingProcess(TextWriter textWriter, User candidate, CrosswordGame crosswordGame, CodingTest codingTest, QuestionRepository questionRepository) {
        this.textWriter = textWriter;
        this.candidate = candidate;
        this.crosswordGame = crosswordGame;
        this.codingTest = codingTest;
        this.questionRepository = questionRepository;
    }

    public int run() {
        textWriter.write(Story.INTRO.get());
        writeResume();
        if (candidate.isFailed(Map.of("외국어", 50, "개발능력", 50))) {
            return handleFail(Story.RESUME_FAIL.get());
        }
        textWriter.write(Story.RESUME_PASS.get());
        codeTest();
        if (candidate.isFailed(Map.of("코테실력", 60, "CS지식", 60))) {
            return handleFail(Story.TEST_FAIL.get());
        }
        textWriter.write(Story.TEST_PASS.get());
        interview1();
        if (candidate.isFailed(Map.of("PT능력", 70, "CS지식", 70))) {
            return handleFail(Story.INTERVIEW1_FAIL.get());
        }
        textWriter.write(Story.INTERVIEW1_PASS.get());
        interview2();
        if (candidate.isFailed(Map.of("개발능력", 80, "코테실력", 80, "CS지식", 80, "PT능력", 80, "외국어", 80))) {
            return handleFail(Story.INTERVIEW2_FAIL.get());
        }
        textWriter.write(Story.INTERVIEW2_PASS.get());
        candidate.setSuccess(true);
        return 0;
    }

    private int handleFail(String text) {
        textWriter.write(text);
        candidate.addFailCount();
        System.out.println("[1. 🎟️ 로또 사러가기 (처음으로)] [2. ✍️ 서류부터 다시 쓰기]");
        return readValidAnswer(2);
    }

    private void writeResume() {
        textWriter.write(Story.RESUME_START.get());
        if (crosswordGame.run()) {
            changeStats(Map.of("외국어", 5, "개발능력", 5));
        }
        textWriter.write(Story.RESUME_AFTER_GAME.get());
    }

    private void codeTest() {
        textWriter.write(Story.TEST_START.get());
        if (codingTest.run()) {
            changeStats(Map.of("코테실력", 5, "CS지식", 5));
        }
        textWriter.write(Story.TEST_AFTER_GAME.get());
    }

    private void interview1() {
        textWriter.write(Story.INTERVIEW1_START.get());
        Question question = questionRepository.getRandomQuestion1();
        print(question);
        int pick = readValidAnswer(question.options.size());
        if (question.answer == pick) {
            textWriter.write("✅ 정답입니다! 🎉👏💯");
            changeStats(Map.of("PT능력", 5, "개발능력", 5));
        } else {
            textWriter.write("⚠️ 오답입니다!");
        }
        textWriter.write(Story.INTERVIEW1_AFTER_GAME.get());
    }

    private void interview2() {
        textWriter.write(Story.INTERVIEW2_START.get());
        Question question = questionRepository.getRandomQuestion2();
        print(question);
        readValidAnswer(question.options.size());
        changeStats(Map.of("CS지식", 5, "코테실력", 5, "건강", 5, "외국어", 5, "개발능력", 5, "PT능력", 5));
        textWriter.write(Story.INTERVIEW2_AFTER_GAME.get());
    }

    private int readValidAnswer(int optionCount) {
        while (true) {
            try {
                System.out.print(">> ");
                int pick = Integer.parseInt(TextReader.readLine()) - 1;
                if (pick < 0 || pick >= optionCount) {
                    System.out.println("⚠️ 입력 값이 보기 범위를 벗어났습니다. 다시 시도하세요.");
                    continue;
                }
                return pick;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ 숫자를 입력해주세요.");
            }
        }
    }

    private void print(Question question) {
        System.out.println(question.name);
        int len = question.options.size();
        for (int i = 0; i < len; i++) {
            System.out.println(" > " + (i + 1) + " : " + question.options.get(i));
        }
    }

    private void changeStats(Map<String, Integer> stats) {
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            candidate.updateStat(entry.getKey(), entry.getValue());
        }
    }
}
