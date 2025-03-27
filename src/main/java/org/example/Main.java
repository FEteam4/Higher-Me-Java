package org.example;
import java.util.List;
import java.util.Scanner;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {

    static Scanner sc = new Scanner(System.in);

    static final String USER_FILE_PATH = "C:\\Users\\kopo\\Desktop\\HigherMe";

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== 시작 CLI ===");
            System.out.println("[1. 메뉴얼] [2. 사용자 등록] [3. 서비스 실행] [4. 결과] [5. 종료]");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: // 1. 메뉴얼
                    showManual();
                    break;
                case 2: // 2. 사용자 등록
                    registerUser();
                    break;
                case 3: // 3. 서비스 실행
//                    runService();
                    break;
                case 4: // 4. 결과
                    showResults();
                    break;
                case 5: // 5. 종료
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    static void registerUser() {
        sc.nextLine(); // flush
        System.out.print("이름 입력: ");
        String name = sc.nextLine();
        System.out.print("성별 입력: ");
        String gender = sc.nextLine();
        User currentUser = new User(name, gender);
        UserFileManager.appendUser(currentUser);
        System.out.println("등록 완료!");
    }

    static void showManual() {
        System.out.println("이 게임은 활동을 통해 스탯을 키우고 채용 전형을 통과하여 최종 합격을 목표로 합니다.");
    }

    static void showResults() {
        List<ActivityOption> certification = ActivityLoader.getOptionsByNo(1, "certification");
        for (ActivityOption option : certification) {
            System.out.println(option.option);
        }
    }
}