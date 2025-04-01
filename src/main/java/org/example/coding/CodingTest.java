package org.example.coding;

import org.example.coding.controller.GameController;

public class CodingTest {
    public static boolean run() {
        GameController gameController = new GameController();
        boolean result = gameController.start();
        return result;
    }
}
