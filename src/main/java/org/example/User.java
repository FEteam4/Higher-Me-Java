package org.example;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String gender;
    private Map<String, Integer> stats;
    private int failCount = 0;
    private boolean success = false;
    private int totalStats;

    public User(){
        this.stats = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public int getFailCount() {
        return failCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getTotalStats() {
        return stats.values().stream().mapToInt(Integer::intValue).sum();
    }

    public User(String name, String gender) {
        this.name = name;
        this.gender = gender.toLowerCase();
        this.stats = new HashMap<>();

        // 명시적으로 키 설정
        stats.put("개발능력", 50);
        stats.put("코테실력", 50);
        stats.put("CS지식", 50);
        stats.put("PT능력", 50);
        stats.put("외국어", 50);
        stats.put("건강", 100);
    }

    // ✅ 문자열 키 기반으로 스탯 업데이트
    public void updateStat(String key, int value) {
        if (stats.containsKey(key)) {
            int current = stats.get(key);
            int updated = Math.max(0, Math.min(100, current + value)); // 0~100 사이로 제한
            stats.put(key, updated);
        } else {
            System.out.println("존재하지 않는 스탯 키입니다: " + key);
        }
    }

    // 스탯 확인하기
    public void showStats() {
        final String RESET = "\u001B[0m";
        final String GREEN = "\u001B[32m";
        final String RED = "\u001B[31m";
        final String YELLOW = "\u001B[33m";
        final String BLUE = "\u001B[34m";
        final String CYAN = "\u001B[36m";

        int width = 60;
        String genderStr = gender.equalsIgnoreCase("m") ? "남자" : gender.equalsIgnoreCase("f") ? "여자" : "기타";

        // 위 테두리
        System.out.println(CYAN + "╔" + "═".repeat(width - 2) + "╗" + RESET);

        printCenter(width, CYAN + "🌟 사용자 정보 🌟" + RESET);
        printCenter(width, "");

        printCenter(width, "🎓 이름: " + GREEN + name + RESET + "   🚻 성별: " + BLUE + genderStr + RESET);
        printCenter(width, "");

        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            int barLen = value / 10;
            String bar = "█".repeat(barLen) + " ".repeat(10 - barLen);
            String line = String.format("📊 %-5s: %s%3d%s │%s│", key, YELLOW, value, RESET, bar);
            printCenter(width, line);
        }

        printCenter(width, "");
        printCenter(width, "💥 실패 횟수: " + RED + failCount + RESET);

        String result = success
                ? "🎉 최종 결과: " + GREEN + "✅ 합격!" + RESET
                : "😢 최종 결과: " + RED + "❌ 불합격" + RESET;
        printCenter(width, result);

        // 아래 테두리
        System.out.println(CYAN + "╚" + "═".repeat(width - 2) + "╝" + RESET);
    }

    // ANSI 컬러코드 제거용
    private String stripAnsi(String s) {
        return s.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    // 이모지/한글 폭 고려한 길이 계산
    private int visualLength(String s) {
        String noAnsi = stripAnsi(s);
        int len = 0;
        for (int i = 0; i < noAnsi.length(); i++) {
            char ch = noAnsi.charAt(i);
            if (Character.UnicodeScript.of(ch).name().matches("HAN|HANGUL|HIRAGANA|KATAKANA") ||
                    Character.UnicodeBlock.of(ch).toString().contains("EMOJI")) {
                len += 2;
            } else {
                len += 1;
            }
        }
        return len;
    }

    // 가운데 정렬 출력 함수
    private void printCenter(int width, String text) {
        int len = visualLength(text);
        int pad = (width - len) / 2;
        System.out.println(" ".repeat(Math.max(pad, 0)) + text);
    }

    public boolean isFailed(Map<String, Integer> cutoff) {
        for (Map.Entry<String, Integer> entry : cutoff.entrySet()) {
            // cutoff를 넘지 못하면
            if (stats.get(entry.getKey()) < entry.getValue()) {
                return true;
            }
        }
        return false;
    }

    public void addFailCount() {
        failCount++;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
