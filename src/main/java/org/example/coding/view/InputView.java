package org.example.coding.view;

import java.util.Scanner;

public class
InputView {
    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String getCommand() {
        System.out.print("🧱 블록 이동 (a: 왼쪽, d: 오른쪽, s: 아래, w: 위, q: 정답 확인하기✅): ");
        return scanner.nextLine();
    }
}
