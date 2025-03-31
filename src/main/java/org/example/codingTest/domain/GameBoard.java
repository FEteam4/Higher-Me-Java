package org.example.codingTest.domain;

public class GameBoard {
    private char[][] board;
    private Blank[] blanks;
    private int width;
    private int height;

    public GameBoard() {
        this.width = 50;
        this.height = 20;
        this.board = new char[height][width];
        this.blanks = new Blank[]{
                new Blank(6, 5, "static"),
                new Blank(30, 9, "max")
        };
    }

    public char[][] getBoard() {
        return board;
    }

    public Blank[] getBlanks() {
        return blanks;
    }

    public void clearBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void displayMultiplicationTable() {
        String[] code = {
                "                                                                                 ",
                "                                                                                 ",
                "                                                                                 ",
                "  public class Gugudan {",
                "                                                                                 ",
                "      ______ int max = 9;",
                "                                                                                 ",
                "      public static void main(String[] args) {",
                "                                                                                 ",
                "          for(int i = 1; i <= ___; i++) {",
                "                                                                                 ",
                "              for(int j = 1; j <= 9; j++) {",
                "                                                                                 ",
                "                  System.out.print(i x j = (i * j));",
                "              }",
                "              System.out.println();",
                "          }",
                "      }",
                "  }"
        };

        for (int i = 0; i < code.length; i++) {
            for (int j = 0; j < code[i].length() && j < width; j++) {
                board[i][j] = code[i].charAt(j);
            }
        }
    }

    public void fillBlank(Blank blank, String answer) {
        for (int i = 0; i < answer.length(); i++) {
            if (blank.getX() + i < width) {
                board[blank.getY()][blank.getX() + i] = answer.charAt(i);
            }
        }
    }
}