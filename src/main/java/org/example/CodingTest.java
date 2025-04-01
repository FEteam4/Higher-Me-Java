//package org.example;
//import java.util.Scanner;
//
//
//public class CodingTest {
//    static final int WIDTH = 50;
//    static final int HEIGHT = 20;
//    static final char BLOCK_CHAR = '■';
//    static final String RED = "\u001B[31m";
//    static final String GREEN = "\u001B[32m";
//    static final String BLUE = "\u001B[34m";
//    static final String YELLOW = "\u001B[33m";
//    static final String RESET = "\u001B[0m";
//    static final String CYAN = "\u001B[36m";
//    static final String PURPLE = "\u001B[35m";
//    static final String START = "\u001B[40m";
//    static int indexT = 0;
//    static char[][] board = new char[HEIGHT][WIDTH];
//    static int currentX, currentY;
//    static String currentBlockValue;
//    static int currentBlockStartX, currentBlockStartY; // 블록 시작 위치 저장
//    static String[] keywords = {"public", "class", "static", "void", "int", "for"};
//    static String[] variables = {"out", "max"};
//
//    // 색깔 배열
//    static String[] colors = {BLUE, YELLOW};
//
//    // 빈칸 저장 (구구단 코드에서 빈칸 위치 기억)
//    static class Blank {
//        int x, y;
//        String answer;
//
//        Blank(int x, int y, String answer) {
//            this.x = x;
//            this.y = y;
//            this.answer = answer;
//        }
//    }
//
//    static Blank[] blanks = {
//            new Blank(6, 5, "static"),
//            new Blank(30, 9, "max")
//    };
//
//    //이거로 시작
////    public static void main(String[] args) {
////        initializeGame();
////        System.out.println("코딩 테스트를 시작합니다.");
////        System.out.println("방향키를 활용하여 알맞은 공간에 q를 눌러 정답을 맞추세요 !");
////        gameLoop();
////    }
//
//    public static boolean run() {
//        initializeGame();
//        System.out.println("코딩 테스트를 시작합니다.");
//        System.out.println("방향키를 활용하여 알맞은 공간에 q를 눌러 정답을 맞추세요 !");
//        return gameLoop();
//    }
//
//    // 게임 초기화
//    static void initializeGame() {
//        clearBoard();
//        displayMultiplicationTable();
//        generateNewBlock();
//    }
//
//    // 보드 초기화
//    static void clearBoard() {
//        for (int i = 0; i < HEIGHT; i++) {
//            for (int j = 0; j < WIDTH; j++) {
//                board[i][j] = ' ';
//            }
//        }
//    }
//
//    // 구구단 코드 표시
//    static void displayMultiplicationTable() {
//        String[] code = {
//                "                                                                                 ",
//                "                                                                                 ",
//                "                                                                                 ",
//                "  public class Gugudan {",
//                "                                                                                 ",
//                "      ______ int max = 9;",
//                "                                                                                 ",
//                "      public static void main(String[] args) {",
//                "                                                                                 ",
//                "          for(int i = 1; i <= ___; i++) {",
//                "                                                                                 ",
//                "              for(int j = 1; j <= 9; j++) {",
//                "                                                                                 ",
//                "                  System.out.print(i x j = (i * j));",
//                "              }",
//                "              System.out.println();",
//                "          }",
//                "      }",
//                "  }"
//        };
//
//        for (int i = 0; i < code.length; i++) {
//            for (int j = 0; j < code[i].length() && j < WIDTH; j++) {
//                board[i][j] = code[i].charAt(j);
//            }
//        }
//    }
//
//    // 게임 루프
//    static boolean gameLoop() {
//        Scanner scanner = new Scanner(System.in);
//        int gameRunning = 0;
//        boolean result = false;
//        while (gameRunning < 2) {
//            printBoard();
//            System.out.print("블록 이동 (a: 왼쪽, d: 오른쪽, s: 아래, w: 위, q: 정답 확인하기): ");
//            String command = scanner.nextLine();
//
//            if (command.equals("a")) {
//                moveBlock(-1, 0);
//            } else if (command.equals("d")) {
//                moveBlock(1, 0);
//            } else if (command.equals("s")) {
//                moveBlock(0, 1);
//            } else if (command.equals("w")) {
//                moveBlock(0, -1);
//            } else if (command.equals("q")) {
//                result = checkAnswer();
//                gameRunning++;
//            }
//        }
//        System.out.println("게임 종료!");
//        return result;
//    }
//
//    // 블록 이동
//    static void moveBlock(int dx, int dy) {
//        int newX = currentX + dx;
//        int newY = currentY + dy;
//
//        if (newX >= 0 && newX < WIDTH && newY >= 0 && newY < HEIGHT) {
//            currentX = newX;
//            currentY = newY;
//        }
//    }
//
//    // 정답 확인 여기서 stats 변화랑 다음 페이지 연결 하시면 됩니다.
//    static boolean checkAnswer() {
//        boolean isCorrect = false;
//        for (Blank blank : blanks) {
//            if (currentX == blank.x && currentY == blank.y) {
//                if (currentBlockValue.equals(blank.answer)) {
//                    System.out.println(GREEN + "정답!" + RESET);
//                    for (int i = 0; i < blank.answer.length(); i++) {
//                        if (blank.x + i < WIDTH) {
//                            board[blank.y][blank.x + i] = blank.answer.charAt(i);
//                        }
//                    }
//                    generateNewBlock();
//                    isCorrect = true;
//                    break;
//                }
//            }
//        }
//        if (!isCorrect) {
//            System.out.println(RED + "오답!" + RESET);
//            generateNewBlock();
//        }
//        return isCorrect;
//    }
//
//    // 새 블록 생성
//    static void generateNewBlock() {
//        String[] values = {"static", "max", "dummy"};
//        currentBlockValue = values[indexT++];
//        currentX = WIDTH / 2;
//        currentY = 0;
//        currentBlockStartX = currentX;
//        currentBlockStartY = currentY;
//    }
//
//    static void printBoard() {
//        String border = CYAN + "+" + "-".repeat(WIDTH + 2) + "+" + RESET;
//        System.out.println(border);
//        for (int i = 0; i < HEIGHT; i++) {
//            System.out.print(CYAN + " |" + RESET);
//            for (int j = 0; j < WIDTH; ) {
//                if (i == currentY && j == currentX) {
//                    System.out.print(getColor(currentBlockValue) + currentBlockValue + RESET);
//                    j += currentBlockValue.length();
//                } else {
//                    String color = "";
//                    String matchedWord = null;
//
//                    for (String keyword : keywords) {
//                        if (isExactMatch(i, j, keyword)) {
//                            color = BLUE;
//                            matchedWord = keyword;
//                            break;
//                        }
//                    }
//
//                    if (matchedWord == null) {
//                        for (String variable : variables) {
//                            if (isExactMatch(i, j, variable)) {
//                                color = PURPLE;
//                                matchedWord = variable;
//                                break;
//                            }
//                        }
//                    }
//
//                    if (matchedWord == null && isString(i, j)) {
//                        color = GREEN;
//                        matchedWord = getString(i, j);
//                    }
//
//                    if (matchedWord == null && isNumber(i, j)) {
//                        color = YELLOW;
//                        matchedWord = getNumber(i, j);
//                    }
//
//                    if (matchedWord != null) {
//                        System.out.print(color + matchedWord + RESET);
//                        j += matchedWord.length();
//                    } else {
//                        System.out.print(board[i][j]);
//                        j++;
//                    }
//                }
//            }
//            System.out.println(CYAN + " |" + RESET);
//        }
//        System.out.println(border);
//        System.out.println("현재 블록: " + getColor(currentBlockValue) + currentBlockValue + RESET + " (시작 위치: " + currentBlockStartX + ", " + currentBlockStartY + ")");
//    }
//
//    static String getColor(String value) {
//        if (value.equals("private")) {
//            return colors[0];
//        } else if (value.equals("max")) {
//            return colors[1];
//        }
//        return RESET;
//    }
//
//    static boolean isExactMatch(int i, int j, String s) {
//        if (s == null) return false;
//        if (j + s.length() > WIDTH) return false;
//        if (!startsWith(i, j, s)) return false;
//
//        if (j > 0 && Character.isLetterOrDigit(board[i][j - 1])) return false;
//        if (j + s.length() < WIDTH && Character.isLetterOrDigit(board[i][j + s.length()])) return false;
//
//        return true;
//    }
//
//    static boolean startsWith(int i, int j, String s) {
//        if (j + s.length() > WIDTH) return false;
//        for (int k = 0; k < s.length(); k++) {
//            if (j + k >= WIDTH || board[i][j + k] != s.charAt(k)) return false;
//        }
//        return true;
//    }
//
//    static String getString(int i, int j) {
//        int start = j;
//        while (start > 0 && board[i][start - 1] != '"') {
//            start--;
//        }
//        int end = j;
//        while (end < WIDTH && board[i][end] != '"') {
//            end++;
//        }
//        return new String(board[i], start, end - start + 1);
//    }
//
//    static String getNumber(int i, int j) {
//        int start = j;
//        while (start > 0 && Character.isDigit(board[i][start - 1])) {
//            start--;
//        }
//        int end = j;
//        while (end < WIDTH && Character.isDigit(board[i][end])) {
//            end++;
//        }
//        return new String(board[i], start, end - start);
//    }
//
//    static boolean isString(int i, int j) {
//        return board[i][j] == '"' || (j > 0 && board[i][j - 1] == '"' && getString(i, j).indexOf('"', j - getString(i, j).indexOf('"')) > 0);
//    }
//
//    static boolean isNumber(int i, int j) {
//        return Character.isDigit(board[i][j]);
//    }
//}