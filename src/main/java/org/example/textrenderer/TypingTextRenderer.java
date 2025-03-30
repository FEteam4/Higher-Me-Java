package org.example.textrenderer;

import java.util.Scanner;

public class TypingTextRenderer implements TextRenderer {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void render(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            typeLine(line);
            waitForEnter();
        }
    }

    private void typeLine(String line) {
        for (char c : line.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void waitForEnter() {
//        System.out.print("← Enter를 눌러 계속...");
        scanner.nextLine(); // 사용자 입력 대기
        clearLine();
    }

    private void clearLine() {
        System.out.print("\r");
        System.out.print(" ".repeat(30));
        System.out.print("\r");
    }
}
