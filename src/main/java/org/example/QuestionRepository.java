package org.example;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    List<Question> questions1;
    List<Question> questions2;

    public QuestionRepository() {
        questions1 = new ArrayList<>();
        questions1.add(new Question("자바의 기본 자료형 중 참조형이 아닌 것은 무엇인가요?", List.of("int", "String", "Integer", "List"), 0));
        questions1.add(new Question("다음 중 오버로딩에 해당하는 것은 무엇인가요?", List.of("동일한 이름의 메서드를 다른 클래스에서 정의하는 것", "메서드를 상속받아 재정의하는 것", "메서드 이름은 같지만 매개변수의 수나 타입이 다른 것", "인터페이스의 메서드를 구현하는 것"), 2));
        questions1.add(new Question("다음 중 자바에서 static 키워드의 사용 예시로 올바른 것은 무엇인가요?", List.of("객체마다 독립적인 값이 필요한 경우", "클래스 수준에서 공유되는 변수나 메서드를 정의할 때", "외부 클래스에 접근하기 위해 반드시 필요한 경우", "예외 처리를 위해 반드시 선언하는 키워드"), 1));
        questions1.add(new Question("자바 인터페이스에 대한 설명으로 틀린 것은 무엇인가요?", List.of("다중 구현이 가능하다", "생성자를 가질 수 없다", "변수 선언 시 값을 변경할 수 있다", "메서드는 기본적으로 추상 메서드이다"), 2));
        questions1.add(new Question("자바에서 null 값을 가질 수 없는 타입은 무엇인가요?", List.of("String", "Integer", "int", "Object"), 2));

        questions2 = new ArrayList<>();
        questions2.add(new Question("동료가 마감 기한을 넘겼을 때, 나는?", List.of("동료에게 이유를 물어보고 돕는다.", "상황을 파악하고 도울 방법을 찾는다.", "팀장에게 알리고 해결책을 논의한다.", "다음번엔 주의하라고 조언하고 대신 처리한다."), 0));
        questions2.add(new Question("팀 내에서 의견 충돌이 발생했을 때, 나는?", List.of("서로의 의견을 듣고 중재하며 합의점을 찾는다.", "상황을 고려해 상사의 판단을 따른다.", "내 입장이 맞다고 생각하면 설득을 시도한다.", "굳이 논쟁에 끼지 않고 가만히 있는다."), 0));
        questions2.add(new Question("예상치 못한 문제가 발생했을 때, 나는?", List.of("팀원들과 함께 해결 방법을 찾는다.", "빠르게 해결한 후 공유하여 진행한다.", "상사나 선배에게 조언을 구한다.", "내 업무와 관련이 없으면 상황을 지켜본다."), 0));
        questions2.add(new Question("회의 중 내 의견이 무시당했을 때, 나는?", List.of("다시 한번 논리적으로 설명하며 설득한다.", "회의 후 개별적으로 의견을 전달한다.", "중요한 의견이라면 강조하여 다시 말한다.", "별로 신경 쓰지 않고 넘어간다."), 0));
        questions2.add(new Question("팀원 중 한 명이 일을 제대로 하지 않을 때, 나는?", List.of("조용히 도와주면서 문제를 해결하려고 한다.", "필요하면 피드백을 주고 개선을 유도한다.", "내 역할에 집중하며 상황을 지켜본다.", "팀장이나 리더와 논의하여 해결 방법을 찾는다."), 0));
        questions2.add(new Question("팀 프로젝트에서 동료가 실수를 했을 때, 나는?", List.of("문제를 해결하고 나중에 따로 피드백한다.", "즉시 함께 해결책을 찾는다.", "직접 해결하기보다 팀장과 논의한다.", "팀의 흐름에 따라 유연하게 대응한다."), 0));
    }

    public Question getRandomQuestion1() {
        return questions1.get((int) (Math.random() * questions1.size() % questions1.size()));
    }

    public Question getRandomQuestion2() {
        return questions2.get((int) (Math.random() * questions2.size() % questions2.size()));
    }
}
