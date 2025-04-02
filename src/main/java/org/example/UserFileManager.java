package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserFileManager {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File file = loadFile();

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

    // ✅ 전체 사용자 업데이트
    public static void writeUsers(List<User> users) {
        try {
            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(file, users);
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

    private static File loadFile() {
        Path location = null;
        try {
            location = Paths.get(Main.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());
        } catch (URISyntaxException e) {
            System.out.println("파일 읽기 실패");
        }

        // .class일 경우: out/production/xxx/
        // .jar일 경우: myapp.jar 위치
        Path projectRoot = location.toFile().isFile()
                ? location.getParent()        // JAR 실행 시
                : location.getParent().getParent().getParent(); // 개발 환경일 경우 (out/production/xxx)

        Path filePath = projectRoot.resolve("users.json");
        return filePath.toFile();
    }
}
