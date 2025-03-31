package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ActivityService {
    private static int activityCount = 0; // 활동 실행 횟수

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
            UserFileManager.writeUsers(Main.users);         // 사용자 스탯 수정 사항 파일에 반영
            activityCount++;
        } else {
            System.out.println("잘못된 입력입니다.");
        }
    }

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

        List<ActivityOption> options = ActivityLoader.getOptionsByNo(randomNo, fileName);

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

        double rand = ThreadLocalRandom.current().nextDouble();
        if (rand <= selected.prob) {
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
