package org.example.io;

import java.util.Scanner;

public class TypingTextWriter implements TextWriter {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void write(String text) {
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
                Thread.sleep(40);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.flush(); // 🔑 모든 출력 강제 완료
    }

    private void waitForEnter() {
        System.out.println(" ⏎ Enter");
        //메인 스레드를 잠시 중단하고 싶어
//        scanner.nextLine();
//        clearLine();
    }

    private void clearLine() {
        System.out.print("\r");
        System.out.print(" ".repeat(30));
        System.out.print("\r");
    }
}
