package org.example.codingTest;

import org.example.codingTest.controller.GameController;

public class CodingTest {
    public static boolean run() {
        GameController gameController = new GameController();
        boolean result = gameController.start();
        return result;
    }
}