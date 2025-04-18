package org.example.coding.controller;
import org.example.coding.domain.BlockManager;
import org.example.coding.domain.GameBoard;
import org.example.coding.domain.Blank;
import org.example.coding.domain.Block;
import org.example.coding.view.InputView;
import org.example.coding.view.OutputView;

public class GameController {
    private GameBoard board;
    private BlockManager blockManager;
    private InputView inputView;
    private OutputView outputView;
    private int completedAnswers;

    public GameController() {
        this.board = new GameBoard();
        this.blockManager = new BlockManager();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.completedAnswers = 0;
    }

    public boolean start() {
        initializeGame();
        outputView.displayStartMessage();
        return gameLoop();
    }


    private void initializeGame() {
        board.clearBoard();
        board.displayMultiplicationTable();
        blockManager.generateNewBlock();
    }

    private boolean gameLoop() {
        while (completedAnswers < 2) {
            outputView.printBoard(board, blockManager.getCurrentBlock());
            String command = inputView.getCommand();

            switch (command) {
                case "a":
                    blockManager.moveBlock(-1, 0);
                    break;
                case "d":
                    blockManager.moveBlock(1, 0);
                    break;
                case "s":
                    blockManager.moveBlock(0, 1);
                    break;
                case "w":
                    blockManager.moveBlock(0, -1);
                    break;
                case "q":
                    checkAnswer();
                    break;
            }
        }
        return true;
    }

    private boolean checkAnswer() {
        boolean isCorrect = false;
        Block currentBlock = blockManager.getCurrentBlock();
        completedAnswers++;
        for (Blank blank : board.getBlanks()) {
            if (currentBlock.getX() == blank.getX() && currentBlock.getY() == blank.getY()) {
                if (currentBlock.getValue().equals(blank.getAnswer())) {
                    outputView.displayCorrectAnswer();
                    board.fillBlank(blank, currentBlock.getValue());
                    blockManager.generateNewBlock();
                    isCorrect = true;
                    break;
                }
            }
        }

        if (!isCorrect) {
            outputView.displayIncorrectAnswer();
            blockManager.generateNewBlock();
            return false;
        }
        return true;
    }
}