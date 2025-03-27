package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ActivityLoader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<ActivityOption> getOptionsByNo(int no, String fileName) {
        try {
            // resources/activity/health.json 형식으로 경로 구성
            String path = "activity/" + fileName +".json";

            // 리소스 파일을 InputStream으로 가져오기
            InputStream is = ActivityLoader.class.getClassLoader().getResourceAsStream(path);

            if (is == null) {
                System.err.println("리소스를 찾을 수 없습니다: " + path);
                return Collections.emptyList();
            }

            List<ActivityOption> allOptions = mapper.readValue(is, new TypeReference<List<ActivityOption>>() {});
            return allOptions.stream()
                    .filter(opt -> opt.no == no)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.err.println("[" + fileName + "] JSON 파싱 오류: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
