// 분리해놓은 것! Main에서 run() 으로 실행하면 됨

package org.example;
import java.util.*;

public class CrosswordGame {
    static class WordEntry {
        int number;
        String answer;
        String direction;
        int row, col;
        String hint;

        WordEntry(int number, String answer, String direction, int row, int col, String hint) {
            this.number = number;
            this.answer = answer;
            this.direction = direction;
            this.row = row;
            this.col = col;
            this.hint = hint;
        }
    }

    static final int ROWS = 8;
    static final int COLS = 10;
    static char[][] grid = new char[ROWS][COLS];
    static String[][] labels = new String[ROWS][COLS];
    static int[][] colorMap = new int[ROWS][COLS];
    static boolean[][] isWhite = new boolean[ROWS][COLS];
    static List<WordEntry> words = new ArrayList<>();

    public static boolean run() {
        Scanner sc = new Scanner(System.in);
        initGrid();
        initWords();
        fillColorMap();

        System.out.println("🧩 크로스워드 퍼즐 게임을 시작합니다!");

        for (WordEntry w : words) {
            printBoard();
            System.out.printf("\n%d번 힌트: %s\n", w.number, w.hint);
            System.out.printf("%d번 단어를 입력하세요 (%s): ", w.number, w.direction.equals("A") ? "가로" : "세로");
            String inputWord = sc.nextLine().trim().toUpperCase();
            if (inputWord.equals(w.answer)) {
                fillWord(w);
                System.out.println("✅ 정답입니다!\n");
            } else {
                System.out.println("❌ 오답입니다. 정답을 공개합니다!");
                for (WordEntry word : words) fillWord(word);
                printBoard();
                return false;
            }
        }

        System.out.println("\n🎉 퍼즐을 완성했습니다! 축하합니다!");
        printBoard();
        return true;
    }

    static void initGrid() {
        for (int i = 0; i < ROWS; i++) {
            Arrays.fill(grid[i], ' ');
            Arrays.fill(labels[i], "");
            Arrays.fill(colorMap[i], 0);
        }
    }

    static void initWords() {
        words.add(new WordEntry(1, "OBJECT", "D", 0, 4, "프로그래밍에서 클래스로부터 생성된 실제 인스턴스"));
        words.add(new WordEntry(2, "CLASS", "D", 1, 1, "객체를 생성하기 위한 설계도"));
        words.add(new WordEntry(3, "ARRAY", "A", 7, 4, "동일한 타입의 데이터를 순차적으로 저장하는 자료구조"));
        words.add(new WordEntry(4, "BINARY", "D", 2, 8, "컴퓨터가 이해하는 2진법 숫자 체계(0과 1)"));
        words.add(new WordEntry(5, "CACHE", "A", 3, 0, "데이터를 임시로 저장하는 고속 메모리 공간"));
        words.add(new WordEntry(6, "THREAD", "A", 5, 4, "하나의 프로세스 내에서 실행되는 흐름 단위"));

        for (WordEntry w : words) {
            labels[w.row][w.col] = String.valueOf(w.number);
        }
    }

    static void fillColorMap() {
        for (WordEntry w : words) {
            for (int i = 0; i < w.answer.length(); i++) {
                int r = w.direction.equals("A") ? w.row : w.row + i;
                int c = w.direction.equals("A") ? w.col + i : w.col;
                isWhite[r][c] = true;
                colorMap[r][c] = w.number;
            }
        }
    }

    static void fillWord(WordEntry w) {
        for (int i = 0; i < w.answer.length(); i++) {
            int r = w.direction.equals("A") ? w.row : w.row + i;
            int c = w.direction.equals("A") ? w.col + i : w.col;
            grid[r][c] = w.answer.charAt(i);
        }
    }

    static void printBoard() {
        String RESET = "\u001B[0m";
        System.out.println("\n   +" + "---+".repeat(COLS));
        for (int r = 0; r < ROWS; r++) {
            System.out.print("   |");
            for (int c = 0; c < COLS; c++) {
                if (!isWhite[r][c]) {
                    System.out.print("\u001B[40m   \u001B[0m|");
                } else {
                    String label = labels[r][c];
                    char ch = grid[r][c];
                    String color = getColorCode(colorMap[r][c]);

                    if (!label.isEmpty()) {
                        String superscript = switch (label) {
                            case "1" -> "¹";
                            case "2" -> "²";
                            case "3" -> "³";
                            case "4" -> "⁴";
                            case "5" -> "⁵";
                            case "6" -> "⁶";
                            default -> label;
                        };
                        System.out.printf("%s%s%c %s|", color, superscript, ch == ' ' ? ' ' : ch, RESET);
                    } else {
                        System.out.printf("%s %c %s|", color, ch == ' ' ? ' ' : ch, RESET);
                    }



//                    if (!label.isEmpty()) {
//                        System.out.printf("%s%s%-2s%s|", color, label, ch == ' ' ? " " : ch, RESET);
//                    } else {
//                        System.out.printf("%s %c %s|", color, ch == ' ' ? ' ' : ch, RESET);
//                    }
                }
            }
            System.out.println("\n   +" + "---+".repeat(COLS));
        }
    }

    static String getColorCode(int number) {
        return switch (number) {
            case 1 -> "\u001B[44m"; // 파랑
            case 2 -> "\u001B[42m"; // 초록
            case 3 -> "\u001B[43m"; // 노랑
            case 4 -> "\u001B[46m"; // 시안
            case 5 -> "\u001B[41m"; // 빨강
            case 6 -> "\u001B[45m"; // 보라
            default -> "";
        };
    }
}
