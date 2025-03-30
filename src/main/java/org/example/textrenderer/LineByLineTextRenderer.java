package org.example.textrenderer;

import java.util.Scanner;

public class LineByLineTextRenderer implements TextRenderer {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void render(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            System.out.print(line);
            waitForEnter();
            clearLineFallback(line);
        }
    }

    private void waitForEnter() {
//        System.out.print("  ← Enter를 눌러 계속...");
        scanner.nextLine(); // 사용자 입력 대기
    }

    private void clearLineFallback(String line) {
        // 캐리지 리턴으로 이동 후 공백 덮어쓰기
        System.out.print("\r");
        System.out.print(" ".repeat(line.length() + 20)); // 안내 문구까지 덮어쓰기
        System.out.print("\r");
    }
}

