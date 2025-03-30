package org.example;

import org.example.textrenderer.TextRenderer;

public class RecruitingProcess {

    private TextRenderer textRenderer;
    private User candidate;
    private CrosswordGame1 crosswordGame; // 서류 게임
    // todo: 코테 테트리스 필드 추가

    public RecruitingProcess(TextRenderer textRenderer, User candidate, CrosswordGame1 crosswordGame) {
        this.textRenderer = textRenderer;
        this.candidate = candidate;
        this.crosswordGame = crosswordGame;
    }

    public void run() {
        return;
    }
}
