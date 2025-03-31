package org.example.codingTest.domain;

public class Block {
    private int x;
    private int y;
    private int startX;
    private int startY;
    private String value;

    public Block(int x, int y, String value) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.value = value;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public String getValue() {
        return value;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}