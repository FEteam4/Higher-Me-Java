package org.example.coding.domain;

public class BlockManager {
    private Block currentBlock;
    private int blockIndex;
    private final String[] blockValues = {"static", "max"};

    public BlockManager() {
        this.blockIndex = 0;
        this.currentBlock = new Block(50 / 2, 0, blockValues[0]);
    }

    public void generateNewBlock() {
        if(blockIndex >= blockValues.length) {
            return;
        }
        String value = blockValues[blockIndex++];
        currentBlock = new Block(50 / 2, 0, value);
    }

    public void moveBlock(int dx, int dy) {
        int newX = currentBlock.getX() + dx;
        int newY = currentBlock.getY() + dy;

        if (newX >= 0 && newX < 50 && newY >= 0 && newY < 20) {
            currentBlock.setPosition(newX, newY);
        }
    }


    public Block getCurrentBlock() {
        return currentBlock;
    }
}