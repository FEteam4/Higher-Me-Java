package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
            String input = sc.nextLine();
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
//                     CrosswordGame1.run(); // Crossword VER. CrosswordGame1 실행
                    break;
                case 4: // 4. 결과
                    //showResults();
                    break;
                case 5: // 5. 종료
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    static void showManual() {
        System.out.println("🎯 Higher Me!");
        System.out.println("활동을 통해 스펙을 키우고 최종 합격을 달성하세요!");
        System.out.println("🚀 활동 종류: 코딩테스트, 자격증, 동아리, 인턴, 운동, 기타");
        System.out.println("📈 각 활동은 능력치를 높이거나 낮출 수 있습니다.");
        System.out.println("⚠️ 활동 결과는 랜덤 요소가 포함되어 있으니 주의하세요!");
        System.out.println("📝 전형 단계: 서류전형 → 필기전형 → 실무면접 → 임원면접");
        System.out.println("🍀 앞으로의 행운을 빕니다!");
    }

    static void registerUser() {
        sc.nextLine(); // flush
        System.out.print("이름 입력: ");
        String name = sc.nextLine();
        System.out.print("성별 입력: ");
        String gender = sc.nextLine();
        currentUser = new User(name, gender);
        UserFileManager.appendUser(currentUser);
        System.out.println("등록 완료!");
    }

    static void runService() {
        while (true) {
            System.out.println("\n[1. 활동 💡] [2. 채용 📝] [3. 사용자 정보(스탯 확인 가능) 📊] [4. 뒤로가기 🔙]");
            System.out.print("🖱️ 메뉴 선택: ");

            int serviceChoice = Integer.parseInt(sc.nextLine());
            switch (serviceChoice) {
                case 1:
                    if (currentUser == null) {
                        System.out.println("⚠️ 먼저 사용자 등록을 진행하세요.");
                        return;
                    }
                    ActivityService.runActivity(currentUser, sc);
                    break;
                case 2:
                    // runRecruitment();
                    break;
                case 3:
                    currentUser.showStats();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
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