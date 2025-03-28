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
}
