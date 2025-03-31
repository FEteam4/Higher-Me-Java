package org.example;

import org.example.io.LineByLineTextWriter;
import org.example.io.TextReader;

import java.util.Scanner;
import java.util.List;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {

    static List<User> users;
    static Scanner sc = new Scanner(System.in);
    static User currentUser = null; // 현재 등록된 사용자

    public static void main(String[] args) {
        // 프로그램 시작시 파일로부터 유저 리스트 초기화
        users = UserFileManager.readUsers();

        while (true) {
            System.out.println("\n=== ☁️ Higher Me! ☁️===");
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
                case 4: // 4. 랭킹 결과
                    //showResults();
                    break;
                case 5: // 5. 종료
                    TextReader.close();
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
        System.out.println("📝 전형 단계: 서류전형 → 필기전형 → 실무 면접 전형 → 임원 면접 전형");
        System.out.println("🍀 앞으로의 행운을 빕니다!");

    }

    static void registerUser() {
        System.out.print("이름 입력: ");
        String name = TextReader.readLine();

        // 동일한 이름을 가진 기존 유저를 저장. 새로운 유저면 null로 초기화
        currentUser = users.stream().filter(u -> name.equals(u.getName())).findAny().orElse(null);

        //
        if (currentUser != null) {
            System.out.println("❗ 이미 등록된 이름입니다. 기존 유저를 불러옵니다.");
            return;
        }

        System.out.print("성별 입력(m 또는 f로 입력하세요): ");
        String gender = TextReader.readLine();

        currentUser = new User(name, gender);
        users.add(currentUser);
        UserFileManager.appendUser(currentUser);
        System.out.println("🆗 등록 완료되었습니다!");
    }


    static int runProcess() {
       if (currentUserIsNull()) {
           return -1;
       }
        RecruitingProcess process = new RecruitingProcess(
                new LineByLineTextWriter(),
                currentUser,
                new CrosswordGame1(),
                new CodingTest()
        );
        return process.run();
    }

    static void runService() {
        while (true) {
            System.out.println("\n[📝 1. 활동] [💼 2. 채용] [📊 3. 사용자 정보(스탯 확인 가능)] [🔙 4. 뒤로가기]");
            System.out.print("📋 메뉴를 선택하세요: ");

            int serviceChoice = Integer.parseInt(TextReader.readLine());
            switch (serviceChoice) {
                case 1:
                    if (currentUserIsNull()) return;
                    ActivityService.runActivity(currentUser, sc);
                    break;
                case 2:
                    if (runProcess() == 0) {return;}
                    break;
                case 3:
                    if (currentUserIsNull()) return;
                    currentUser.showStats();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    private static boolean currentUserIsNull() {
        if (currentUser == null) {
            System.out.println("⚠️ 먼저 사용자 등록을 진행하세요.");
            return true;
        }
        return false;
    }

//    public static void showResults() {
//        int randomNo = ThreadLocalRandom.current().nextInt(1, 6); // 선지 랜덤 선택
//        List<ActivityOption> certification = ActivityLoader.getOptionsByNo(randomNo, "certification");
//        for (ActivityOption option : certification) {
//            System.out.println(option.option);
//        }
//    }
}