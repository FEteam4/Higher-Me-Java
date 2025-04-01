package org.example.io;

public class LineByLineTextWriter implements TextWriter {

    @Override
    public void write(String text) {
        String[] lines = text.split("\n");
        for (String line : lines) {
            System.out.print(line);
            System.out.print(" (Enter)");
            TextReader.readLine();
            clearLineFallback(line);
        }
    }

    private void clearLineFallback(String line) {
        // 캐리지 리턴으로 이동 후 공백 덮어쓰기
        System.out.print("\r");
        System.out.print(" ".repeat(line.length() + 20)); // 안내 문구까지 덮어쓰기
        System.out.print("\r");
    }
}

