package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ActivityService {
    private static int activityCount = 0; // 활동 실행 횟수

    // 활동 메뉴를 실행하는 메서드
    public static void runActivity(User currentUser, Scanner sc) {
        if (activityCount >= 3) {
            System.out.println("활동은 3번까지 진행할 수 있습니다. 채용에 도전하세요.");
            return;
        }
        System.out.println("\n6가지 활동 중 하나를 선택하세요!");
        System.out.println("활동은 최대 3번까지 가능합니다 (현재 : " + activityCount + "회)");
        System.out.println("[1. 코테] [2. 자격증] [3. 동아리] [4. 인턴] [5. 운동] [6. 기타] [7. 뒤로가기]");
        System.out.print("활동 선택: ");
        int activityChoice = Integer.parseInt(sc.nextLine());
        if (activityChoice == 7) {
            return;
        }
        if (activityChoice >= 1 && activityChoice <= 6) {
            activity(activityChoice, currentUser, sc);
            activityCount++; // 성공/실패 여부와 상관없이 유효한 활동 진행 후 횟수 증가
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

    // 활동 선택에 따른 세부 로직을 수행하는 메서드
    private static void activity(int activityChoice, User currentUser, Scanner sc) {
        String fileName;
        int randomNo;

        switch (activityChoice) {
            case 1:
                fileName = "codingtest";
                randomNo = ThreadLocalRandom.current().nextInt(1, 8);
                break;
            case 2:
                fileName = "certification";
                randomNo = ThreadLocalRandom.current().nextInt(1, 7);
                break;
            case 3:
                fileName = "schoolClub";
                randomNo = ThreadLocalRandom.current().nextInt(1, 6);
                break;
            case 4:
                fileName = "intern";
                randomNo = ThreadLocalRandom.current().nextInt(1, 7);
                break;
            case 5:
                fileName = "exercise";
                randomNo = ThreadLocalRandom.current().nextInt(1, 10);
                break;
            case 6:
                fileName = "others";
                randomNo = ThreadLocalRandom.current().nextInt(1, 13);
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                return;
        }

        // JSON 파일에서 randomNo에 해당하는 옵션들을 로드
        List<ActivityOption> options = ActivityLoader.getOptionsByNo(randomNo, fileName);
        if (options.size() < 2) {
            System.out.println(fileName + " 옵션 데이터가 충분하지 않습니다.");
            return;
        }

        // 옵션을 [1. ~~~] vs [2. ~~~] 형식으로 출력
        System.out.println("\n[1. " + options.get(0).option + "] vs [2. " + options.get(1).option + "]");
        System.out.print("선택 (1 또는 2): ");
        int choice = Integer.parseInt(sc.nextLine());
        ActivityOption selected;
        if (choice == 1) {
            selected = options.get(0);
        } else if (choice == 2) {
            selected = options.get(1);
        } else {
            System.out.println("잘못된 선택입니다.");
            return;
        }

        // 난수를 발생시켜 선택된 옵션의 prob와 비교
        double rand = ThreadLocalRandom.current().nextDouble();
        if (rand > selected.prob) {
            // 성공 시 각 스탯 업데이트
            currentUser.updateStat("개발능력", selected.sw);
            currentUser.updateStat("코테실력", selected.ps);
            currentUser.updateStat("CS지식", selected.cs);
            currentUser.updateStat("PT능력", selected.pt);
            currentUser.updateStat("외국어", selected.eng);
            currentUser.updateStat("건강", selected.health);
            System.out.println("성공했습니다! " + selected.ok);
        } else {
            System.out.println("실패했습니다. " + selected.ng);
        }
    }
}
