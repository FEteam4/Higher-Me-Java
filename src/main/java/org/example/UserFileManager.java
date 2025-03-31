package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File file = new File("users.json");

    // ✅ 사용자 추가
    public static void appendUser(User user) {
        List<User> users = readUsers(); // 기존 사용자 불러오기
        users.add(user);

        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(file, users);
//            System.out.println("사용자 추가 완료: " + user.name);
        } catch (IOException e) {
            System.err.println("파일 저장 실패: " + e.getMessage());
        }
    }

    // ✅ 사용자 읽기
    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("users.json 파일이 존재하지 않습니다.");
            return users;
        }

        try {
            users = mapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            System.err.println("사용자 읽기 실패: " + e.getMessage());
        }

        return users;
    }

    // (예은) 추가
    public static void showUserRanking() {
        List<User> users = readUsers();

        List<User> successfulUsers = users.stream()
                .filter(User::isSuccess)
                .sorted((u1, u2) -> Integer.compare(u2.getTotalStats(), u1.getTotalStats()))
                .toList();

        if (successfulUsers.isEmpty()) {
            System.out.println("✅ 아직 합격한 유저가 없습니다.");
            return;
        }

        System.out.println("🎖️ [합격 유저 순위]");
        int rank = 1;
        for (User user : successfulUsers) {
            String medal = switch (rank) {
                case 1 -> "🥇";
                case 2 -> "🥈";
                case 3 -> "🥉";
                default -> rank + "위";
            };

            System.out.println(medal + " - " + user.getName() +
                    " | 총합 스탯: " + user.getTotalStats() +
                    " | 실패 횟수: " + user.getFailCount());
            rank++;
        }
    }

}
