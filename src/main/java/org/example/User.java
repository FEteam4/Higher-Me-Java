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

    public void showStats() {
        System.out.println("--- 사용자 정보 ---");
        System.out.println("이름: " + name + ", 성별: " + gender);
        for (Map.Entry<String, Integer> entry : stats.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("실패 횟수: " + failCount);
        System.out.println("합격 여부: " + (success ? "합격" : "미합격"));
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

//    public void showSummary() {
//        System.out.println(name + " | 실패: " + failCount + "회 | 스탯 합계: " + getTotalStats());
//    }
}
