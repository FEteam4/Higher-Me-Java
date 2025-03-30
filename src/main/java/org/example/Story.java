package org.example;

public enum Story {

    INTRO("하이어미 컴퍼니에 채용공고가 떴습니다.\n서류 → 필기 → 실무 면접 → 임원 면접 순서로 이어집니다."),

    RESUME_START("서류 전형을 시작합니다."),
    RESUME_AFTER_GAME("게임을 종료합니다.\n결과를 확인하시겠습니까?"),
    // todo: 멘트 수정
    RESUME_PASS("축하합니다. 서류 전형에 합격하셨습니다.\n다음 단계로 이동합니다."),
    RESUME_FAIL("능력치가 부족하여 서류 전형에서 떨어졌습니다."),

    TEST_START("필기 전형을 시작합니다."),
    TEST_AFTER_GAME("게임을 종료합니다.\n결과를 확인하시겠습니까?"),
    TEST_PASS("축하합니다. 필기 전형에 합격하셨습니다.\n다음 단계로 이동합니다."),
    TEST_FAIL("능력치가 부족하여 필기 전형에서 떨어졌습니다."),

    INTERVIEW1_START("실무 면접 전형을 시작합니다."),
    INTERVIEW1_AFTER_GAME("게임을 종료합니다.\n결과를 확인하시겠습니까?"),
    INTERVIEW1_PASS("축하합니다. 실무 면접 전형에 합격하셨습니다.\n다음 단계로 이동합니다."),
    INTERVIEW1_FAIL("능력치가 부족하여 실무 면접 전형에서 떨어졌습니다."),

    INTERVIEW2_START("임원 면접 전형을 시작합니다."),
    INTERVIEW2_AFTER_GAME("게임을 종료합니다.\n결과를 확인하시겠습니까?"),
    INTERVIEW2_PASS("축하합니다. 임원 면접 전형에 합격하셨습니다."),
    INTERVIEW2_FAIL("능력치가 부족하여 임원 면접 전형에서 떨어졌습니다.");

    private final String content;

    Story(String content) {
        this.content = content;
    }

    public String get() {
        return content;
    }
}
