package org.example;

import org.example.io.TextReader;
import org.example.io.TextWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecruitingProcess {

    private static final List<Question> questions1;
    private static final List<Question> questions2;

    private final TextWriter textWriter;
    private final User candidate;
    private final CrosswordGame1 crosswordGame; // 서류 게임
    private final CodingTest codingTest;

    // todo: 문제 바꾸기
    static {
        questions1 = new ArrayList<>();
        questions1.add(new Question("JavaScript에서 'this' 키워드는 무엇을 가리키나요?", List.of("전역 객체", "호출한 객체", "함수 내부의 지역 변수", "모듈 객체"), 1));
        questions1.add(new Question("시간 복잡도가 가장 효율적인 알고리즘은 무엇인가요?", List.of("선택 정렬", "퀵 정렬", "버블 정렬", "삽입 정렬"), 1));
        questions1.add(new Question("데이터베이스 정규화의 주요 목적은 무엇인가요?", List.of("중복 데이터 제거", "성능 최적화", "데이터 암호화", "트랜잭션 처리"), 0));
        questions1.add(new Question("코드를 작성할 때 가장 중요한 원칙은 무엇인가요?", List.of("실행 속도 최적화", "가독성", "최소한의 코드 길이", "기능 확장 가능성"), 1));
        questions1.add(new Question("HTTP 상태 코드 500은 무엇을 의미하나요?", List.of("클라이언트 오류", "리다이렉트", "서버 오류", "요청 성공"), 2));

        questions2 = new ArrayList<>();
        questions2.add(new Question("동료가 마감 기한을 넘겼을 때, 나는?", List.of("동료에게 이유를 물어보고 돕는다.", "상황을 파악하고 도울 방법을 찾는다.", "팀장에게 알리고 해결책을 논의한다.", "다음번엔 주의하라고 조언하고 대신 처리한다."), 0));
        questions2.add(new Question("팀 내에서 의견 충돌이 발생했을 때, 나는?", List.of("서로의 의견을 듣고 중재하며 합의점을 찾는다.", "상황을 고려해 상사의 판단을 따른다.", "내 입장이 맞다고 생각하면 설득을 시도한다.", "굳이 논쟁에 끼지 않고 가만히 있는다."), 0));
        questions2.add(new Question("예상치 못한 문제가 발생했을 때, 나는?", List.of("팀원들과 함께 해결 방법을 찾는다.", "빠르게 해결한 후 공유하여 진행한다.", "상사나 선배에게 조언을 구한다.", "내 업무와 관련이 없으면 상황을 지켜본다."), 0));
        questions2.add(new Question("회의 중 내 의견이 무시당했을 때, 나는?", List.of("다시 한번 논리적으로 설명하며 설득한다.", "회의 후 개별적으로 의견을 전달한다.", "중요한 의견이라면 강조하여 다시 말한다.", "별로 신경 쓰지 않고 넘어간다."), 0));
        questions2.add(new Question("팀원 중 한 명이 일을 제대로 하지 않을 때, 나는?", List.of("조용히 도와주면서 문제를 해결하려고 한다.", "필요하면 피드백을 주고 개선을 유도한다.", "내 역할에 집중하며 상황을 지켜본다.", "팀장이나 리더와 논의하여 해결 방법을 찾는다."), 0));
        questions2.add(new Question("팀 프로젝트에서 동료가 실수를 했을 때, 나는?", List.of("문제를 해결하고 나중에 따로 피드백한다.", "즉시 함께 해결책을 찾는다.", "직접 해결하기보다 팀장과 논의한다.", "팀의 흐름에 따라 유연하게 대응한다."), 0));
    }

    public RecruitingProcess(TextWriter textWriter, User candidate, CrosswordGame1 crosswordGame, CodingTest codingTest) {
        this.textWriter = textWriter;
        this.candidate = candidate;
        this.crosswordGame = crosswordGame;
        this.codingTest = codingTest;
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
        System.out.println("[1. 로또 사러가기 (처음으로)] [2. 서류부터 다시 쓰기]");
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
        Question question = getRandomQuestion(questions1);
        print(question);
        int pick = readValidAnswer(question.options.size());
        if (question.answer == pick) {
            textWriter.write("정답입니다!");
            changeStats(Map.of("PT능력", 5, "개발능력", 5));
        } else {
            textWriter.write("오답입니다!");
        }
        textWriter.write(Story.INTERVIEW1_AFTER_GAME.get());
    }

    private void interview2() {
        textWriter.write(Story.INTERVIEW2_START.get());
        Question question = getRandomQuestion(questions2);
        print(question);
        readValidAnswer(question.options.size());
        changeStats(Map.of("CS지식", 5, "코테실력", 5, "건강", 5, "외국어", 5, "개발능력", 5, "PT능력", 5));
        textWriter.write(Story.INTERVIEW2_AFTER_GAME.get());
    }

    private static Question getRandomQuestion(List<Question> questions1) {
        int len = questions1.size();
        return questions1.get((int) (Math.random() * len % len));
    }

    private int readValidAnswer(int optionCount) {
        while (true) {
            try {
                System.out.print(">> ");
                int pick = Integer.parseInt(TextReader.readLine()) - 1;
                if (pick < 0 || pick >= optionCount) {
                    System.out.println("입력 값이 보기 범위를 벗어났습니다. 다시 시도하세요.");
                    continue;
                }
                return pick;
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
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

    private static class Question {
        String name;
        List<String> options;
        int answer;

        public Question(String name, List<String> options, int answer) {
            this.name = name;
            this.options = options;
            this.answer = answer;
        }
    }
}
