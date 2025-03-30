package org.example;

import org.example.io.LineByLineTextWriter;
import org.example.io.TextReader;

import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {

    static Scanner sc = new Scanner(System.in);
    static final String USER_FILE_PATH = "C:\\Users\\kopo\\Desktop\\HigherMe";
    static User currentUser = null; // 현재 등록된 사용자

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== 시작 CLI ===");
            System.out.println("[📋 1️⃣. 메뉴얼] [🔐 2️⃣. 사용자 등록] [💼 3️⃣. 서비스 실행] [🥇 4️⃣. 결과] [🛑 5️⃣. 종료]");
            String input = TextReader.readLine();
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1: // 1. 메뉴얼
                    showManual();
                    break;
                case 2: // 2. 사용자 등록
                    registerUser();
                    break;
                case 3: // 3. 서비스 실행
                    runService();
                    // WordQuizGame1.runGame(sc); // 빈칸 뚫은 VER. WordQuizGame1 실행
                    //CrosswordGame1.run(); // Crossword VER. CrosswordGame1 실행
                    break;
                case 4: // 4. 결과
                    //showResults();
                    break;
                case 5: // 5. 종료
                    TextReader.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    static void showManual() {
        System.out.println("이 게임은 활동을 통해 스탯을 키우고 채용 전형을 통과하여 최종 합격을 목표로 합니다.");
    }

    static void registerUser() {
        TextReader.readLine(); // flush
        System.out.print("이름 입력: ");
        String name = TextReader.readLine();
        System.out.print("성별 입력: ");
        String gender = TextReader.readLine();
        currentUser = new User(name, gender);
        UserFileManager.appendUser(currentUser);
        System.out.println("등록 완료!");
    }

    static int runProcess() {
        User candidate = new User("test-user", "woman");
        RecruitingProcess process = new RecruitingProcess(new LineByLineTextWriter(), candidate, new CrosswordGame1());
        return process.run();
    }

    static void runService() {
        while (true) {
            System.out.println("\n[1. 활동] [2. 채용] [3. 사용자 정보(스탯 확인 가능)] [4. 뒤로가기]");
            System.out.print("메뉴 선택: ");
            int serviceChoice = Integer.parseInt(TextReader.readLine());
            switch (serviceChoice) {
                case 1:
                    if (currentUser == null) {
                        System.out.println("먼저 사용자 등록을 진행하세요.");
                        return; // 시작 CLI(메인 메뉴)로 복귀
                    }
                    ActivityService.runActivity(currentUser, sc);
                    break;
                case 2:
                    if (runProcess() == 0) {return;}
                    break;
                case 3:
                    currentUser.showStats();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

//    public static void showResults() {
//        int randomNo = ThreadLocalRandom.current().nextInt(1, 6); // 선지 랜덤 선택
//        List<ActivityOption> certification = ActivityLoader.getOptionsByNo(randomNo, "certification");
//        for (ActivityOption option : certification) {
//            System.out.println(option.option);
//        }
//    }
}