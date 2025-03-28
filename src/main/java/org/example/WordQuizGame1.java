// 분리해놓은 것! Main에서 runGame() 으로 실행하면 됨

package org.example;
import java.util.Scanner;

public class WordQuizGame1 {

    public static void runGame(Scanner scanner) {
//        Scanner scanner = new Scanner(System.in); Main.java의 Scanner 사용해야 함

        String[][] puzzle = {
                {" ", "C", "O", "1", "E", " ", " ", " ", " "},
                {" ", " ", " ", "A", " ", " ", " ", " ", " "},
                {" ", " ", " ", "T", " ", " ", " ", "6", " "},
                {"J", "2", "V", "A", " ", " ", " ", "O", " "},
                {" ", "P", " ", " ", " ", " ", " ", "O", " "},
                {"3", "I", "T", " ", "4", "5", "M", "L", " "},
                {" ", " ", " ", " ", "T", " ", " ", " ", " "},
                {" ", " ", " ", " ", "T", " ", "C", "S", "S"},
                {" ", " ", "T", "7", "P", " ", " ", " ", " "}
        };

        System.out.println("\n==== 낱말 퀴즈 ====");
        System.out.println("🧩 빈칸 1️⃣~7️⃣ 에 들어갈 글자를 입력하세요 :\n");
        printPuzzleWithAsciiPipes(puzzle);

        // 입력 및 정답 체크
        String[] answers = new String[7];
        String[] correctAnswers = {"D", "A", "B", "H", "T", "P", "C"};
        for (int i = 0; i < 7; i++) {
            System.out.print("[" + (i + 1) + "] 빈칸에 들어갈 글자를 입력하세요: ");
            answers[i] = scanner.nextLine().trim().toUpperCase();
        }

        boolean allCorrect = true;
        for (int i = 0; i < 7; i++) {
            if (!answers[i].equals(correctAnswers[i])) {
                allCorrect = false;
            }
        }

        if (allCorrect) {
            System.out.println("\n🎉 정답입니다! 모두 맞히셨어요!");
        } else {
            System.out.println("\n😢 오답입니다! 정답을 확인하세요!.");
            for (int i = 0; i < 7; i++) {
                if (!answers[i].equals(correctAnswers[i])) {
                    System.out.println(" - [" + (i + 1) + "] 오답 (정답: " + correctAnswers[i] + ")");
                }
            }
        }
    }

    public static void printPuzzleWithAsciiPipes(String[][] puzzle) {
        int rows = puzzle.length;
        int cols = puzzle[0].length;

        System.out.print("┌");
        for (int i = 0; i < cols - 1; i++) System.out.print("───┬");
        System.out.println("───┐");

        for (int i = 0; i < rows; i++) {
            System.out.print("│");
            for (int j = 0; j < cols; j++) {
                String cell = puzzle[i][j].trim();
                System.out.print(" " + (cell.isEmpty() ? " " : cell) + " │");
            }
            System.out.println();

            if (i < rows - 1) {
                System.out.print("├");
                for (int j = 0; j < cols - 1; j++) System.out.print("───┼");
                System.out.println("───┤");
            }
        }

        System.out.print("└");
        for (int i = 0; i < cols - 1; i++) System.out.print("───┴");
        System.out.println("───┘");
    }
}
