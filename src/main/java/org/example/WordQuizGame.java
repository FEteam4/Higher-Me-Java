// 분리 안 해놓은 것! (사용 안 함!!!!!!!!!!)

package org.example;
import java.util.Scanner;

public class WordQuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 퍼즐 데이터 (ㄱ, ㄴ, ㄷ → 빈칸 표시)
//        String[][] puzzle = {
//                {"함", "ㄱ", " ", " ", " ", " ", " "},
//                {" ", "식", " ", " ", " ", " ", " "},
//                {" ", " ", "자", "ㄴ", " ", " ", " "},
//                {" ", " ", " ", "이", " ", " ", " "},
//                {"배", "ㄷ", " ", "트", " ", " ", " "},
//                {" ", "거", " ", " ", " ", " ", " "},
//                {" ", " ", " ", " ", " ", " ", " "},
//        };

//        String [][] puzzle = {
//                {" ", "C", "O", "1", "E", " ", " "}, // → CODE
//                {" ", " ", " ", "A", " ", " ", " "}, // → DATA
//                {" ", " ", " ", "T", " ", " ", " "},
//                {"J", "2", "V", "A", " ", " ", " "}, // → JAVA
//                {" ", "P", " ", " ", " ", " ", " "}, // → API
//                {"3", "I", "T", " ", " ", " ", " "}  // → BIT
//        };

        String[][] puzzle = {
                {" ", "C", "O", "1", "E", " ", " ", " ", " "}, // CODE
                {" ", " ", " ", "A", " ", " ", " ", " ", " "}, // DATA(↓)
                {" ", " ", " ", "T", " ", " ", " ", "6", " "},
                {"J", "2", "V", "A", " ", " ", " ", "O", " "}, // JAVA
                {" ", "P", " ", " ", " ", " ", " ", "O", " "}, // API(↓)
                {"3", "I", "T", " ", "4", "5", "M", "L", " "}, // BIT, HTML(→)
                {" ", " ", " ", " ", "T", " ", " ", " ", " "}, // HTTP(↓)
                {" ", " ", " ", " ", "T", " ", "C", "S", "S"}, // CSS(→)
                {" ", " ", "T", "7", "P", " ", " ", " ", " "}  //
        };

        // 퍼즐판 출력
        System.out.println("\n==== 낱말 퀴즈 ====");
        System.out.println("퍼즐판 (빈칸 1, 2, 3, 4, 5에 들어갈 글자를 맞혀보세요):\n");

        printPuzzleWithAsciiPipes(puzzle);

        // 정답 입력
        System.out.print("[1] 빈칸에 들어갈 글자를 입력하세요: ");
        String answer1 = scanner.nextLine().trim().toUpperCase();

        System.out.print("[2] 빈칸에 들어갈 글자를 입력하세요: ");
        String answer2 = scanner.nextLine().trim().toUpperCase();

        System.out.print("[3] 빈칸에 들어갈 글자를 입력하세요: ");
        String answer3 = scanner.nextLine().trim().toUpperCase();

        System.out.print("[4] 빈칸에 들어갈 글자를 입력하세요: ");
        String answer4 = scanner.nextLine().trim().toUpperCase();

        System.out.print("[5] 빈칸에 들어갈 글자를 입력하세요: ");
        String answer5 = scanner.nextLine().trim().toUpperCase();

        System.out.print("[6] 빈칸에 들어갈 글자를 입력하세요: ");
        String answer6 = scanner.nextLine().trim().toUpperCase();

        // 정답 체크
        boolean correct1 = answer1.equals("D");
        boolean correct2 = answer2.equals("A");
        boolean correct3 = answer3.equals("B");
        boolean correct4 = answer4.equals("H");
        boolean correct5 = answer5.equals("T");
        boolean correct6 = answer6.equals("P");

        if (correct1 && correct2 && correct3 && correct4 && correct5) {
            System.out.println("\n🎉 정답입니다! 모두 맞히셨어요!");
        } else {
            System.out.println("\n😢 아쉽습니다. 다시 도전해보세요.");
            if (!correct1) System.out.println(" - [1] 오답 (정답: D)");
            if (!correct2) System.out.println(" - [2] 오답 (정답: A)");
            if (!correct3) System.out.println(" - [3] 오답 (정답: B)");
            if (!correct4) System.out.println(" - [4] 오답 (정답: H)");
            if (!correct5) System.out.println(" - [5] 오답 (정답: T)");
            if (!correct6) System.out.println(" - [6] 오답 (정답: P)");
        }

        scanner.close();
    }

    public static void printPuzzleWithAsciiPipes(String[][] puzzle) {
        int size = puzzle.length;

        // 윗줄
        System.out.print("┌");
        for (int i = 0; i < size - 1; i++) {
            System.out.print("───┬");
        }
        System.out.println("───┐");

        for (int i = 0; i < size; i++) {
            System.out.print("│");
            for (int j = 0; j < size; j++) {
                String cell = puzzle[i][j].trim();
                if (cell.isEmpty()) cell = " ";
                System.out.print(" " + cell + " │");
            }
            System.out.println();

            if (i < size - 1) {
                System.out.print("├");
                for (int k = 0; k < size - 1; k++) {
                    System.out.print("───┼");
                }
                System.out.println("───┤");
            }
        }

        // 아랫줄
        System.out.print("└");
        for (int i = 0; i < size - 1; i++) {
            System.out.print("───┴");
        }
        System.out.println("───┘");
    }
}
