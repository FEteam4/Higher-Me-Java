package org.example.codingTest.view;

import org.example.codingTest.controller.GameController;
import org.example.codingTest.domain.Block;
import org.example.codingTest.domain.GameBoard;

public class OutputView {
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";
    private static final String[] keywords = {"public", "class", "static", "void", "int", "for"};
    private static final String[] variables = {"out", "max"};
    private static final String[] colors = {BLUE, YELLOW};

    public void displayStartMessage() {
        System.out.println("코딩 테스트를 시작합니다.");
        System.out.println("방향키를 활용하여 알맞은 공간에 q를 눌러 정답을 맞추세요 !");
    }

    public void displayEndMessage() {
        System.out.println("게임 종료!");
    }

    public void displayCorrectAnswer() {
        System.out.println(GREEN + "정답!" + RESET);
    }

    public void displayIncorrectAnswer() {
        System.out.println(RED + "오답!" + RESET);
    }

    public void printBoard(GameBoard board, Block currentBlock) {

        String border = CYAN + "+" + "-".repeat(50 + 2) + "+" + RESET;
        System.out.println(border);

        for (int i = 0; i < 20; i++) {
            System.out.print(CYAN + " |" + RESET);
            for (int j = 0; j < 50;) {
                if (i == currentBlock.getY() && j == currentBlock.getX()) {
                    System.out.print(getColor(currentBlock.getValue()) + currentBlock.getValue() + RESET);
                    j += currentBlock.getValue().length();
                } else {
                    String color = "";
                    String matchedWord = null;

                    for (String keyword : keywords) {
                        if (isExactMatch(board.getBoard(), i, j, keyword)) {
                            color = BLUE;
                            matchedWord = keyword;
                            break;
                        }
                    }

                    if (matchedWord == null) {
                        for (String variable : variables) {
                            if (isExactMatch(board.getBoard(), i, j, variable)) {
                                color = PURPLE;
                                matchedWord = variable;
                                break;
                            }
                        }
                    }

                    if (matchedWord == null && isString(board.getBoard(), i, j)) {
                        color = GREEN;
                        matchedWord = getString(board.getBoard(), i, j);
                    }

                    if (matchedWord == null && isNumber(board.getBoard(), i, j)) {
                        color = YELLOW;
                        matchedWord = getNumber(board.getBoard(), i, j);
                    }

                    if (matchedWord != null) {
                        System.out.print(color + matchedWord + RESET);
                        j += matchedWord.length();
                    } else {
                        System.out.print(board.getBoard()[i][j]);
                        j++;
                    }
                }
            }
            System.out.println(CYAN + " |" + RESET);
        }
        System.out.println(border);
        System.out.println("현재 블록: " + getColor(currentBlock.getValue()) + currentBlock.getValue() + RESET +
                " (시작 위치: " + currentBlock.getStartX() + ", " + currentBlock.getStartY() + ")");
    }

    private String getColor(String value) {
        if (value.equals("private")) {
            return colors[0];
        } else if (value.equals("max")) {
            return colors[1];
        }
        return RESET;
    }

    private boolean isExactMatch(char[][] board, int i, int j, String s) {
        if (s == null) return false;
        if (j + s.length() > 50) return false;
        if (!startsWith(board, i, j, s)) return false;

        if (j > 0 && Character.isLetterOrDigit(board[i][j - 1])) return false;
        if (j + s.length() < 50 && Character.isLetterOrDigit(board[i][j + s.length()])) return false;

        return true;
    }

    private boolean startsWith(char[][] board, int i, int j, String s) {
        if (j + s.length() > 50) return false;
        for (int k = 0; k < s.length(); k++) {
            if (j + k >= 50 || board[i][j + k] != s.charAt(k)) return false;
        }
        return true;
    }

    private String getString(char[][] board, int i, int j) {
        int start = j;
        while (start > 0 && board[i][start - 1] != '"') {
            start--;
        }
        int end = j;
        while (end < 50 && board[i][end] != '"') {
            end++;
        }
        return new String(board[i], start, end - start + 1);
    }

    private String getNumber(char[][] board, int i, int j) {
        int start = j;
        while (start > 0 && Character.isDigit(board[i][start - 1])) {
            start--;
        }
        int end = j;
        while (end < 50 && Character.isDigit(board[i][end])) {
            end++;
        }
        return new String(board[i], start, end - start);
    }

    private boolean isString(char[][] board, int i, int j) {
        return board[i][j] == '"' || (j > 0 && board[i][j - 1] == '"' &&
                getString(board, i, j).indexOf('"', j - getString(board, i, j).indexOf('"')) > 0);
    }

    private boolean isNumber(char[][] board, int i, int j) {
        return Character.isDigit(board[i][j]);
    }
}
