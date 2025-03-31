package org.example.io;

import java.util.Scanner;

public class TextReader {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static void close() {
        scanner.close();
    }
}
