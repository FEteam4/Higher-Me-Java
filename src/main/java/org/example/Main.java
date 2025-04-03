package org.example;

import org.example.io.LineByLineTextWriter;
import org.example.io.TextReader;
import org.example.coding.CodingTest;
import org.example.io.TypingTextWriter;

import java.util.Comparator;
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
            System.out.println("[📋 1️⃣. 메뉴얼] [🔐 2️⃣. 사용자 등록] [💼 3️⃣. 서비스 실행] [🥇 4️⃣. 랭킹 결과] [🛑 5️⃣. 종료]");
            System.out.print("번호를 입력해주세요: ");
            try {
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
                        break;
                    case 4: // 4. 랭킹 결과
                        showUserRanking(users);
                        break;
                    case 5: // 5. 종료
                        TextReader.close();
                        return;
                    default:
                        System.out.println("⚠️ 잘못된 입력입니다.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("⚠️ 잘못된 입력입니다.");
            }
        }
    }

    static void typeEffect(String message, int delay) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(); // 줄바꿈
    }

    static void showManual() {
        typeEffect("🎯 Higher Me!", 50);
        typeEffect("⏳ 채용 공고가 뜨기 10개월 전으로 시간을 되돌립니다..", 40);
        typeEffect("⏪ 과거로 이동 중... 준비하세요!", 40);
        typeEffect("🕰️ 당신의 여정은 지금부터 시작됩니다.", 40);
        typeEffect("🏁 활동을 통해 스펙을 키우고 최종 합격까지 달려가 볼까요? 🏃💨️", 40);
        typeEffect("🚀 활동 종류: 코딩테스트, 자격증, 동아리, 인턴, 운동, 기타", 40);
        typeEffect("📈 각 활동은 능력치를 높이거나 낮출 수 있습니다.", 40);
        typeEffect("⚠️ 활동 결과는 랜덤 요소가 포함되어 있으니 주의하세요!", 40);
        typeEffect("📝 전형 단계: 서류전형 → 필기전형 → 실무 면접 전형 → 임원 면접 전형", 40);
        typeEffect("🍀 앞으로의 행운을 빕니다!", 50);
    }

    static void registerUser() {
        System.out.print("이름 입력: ");
        String name = TextReader.readLine();

        // 동일한 이름을 가진 기존 유저를 저장. 새로운 유저면 null로 초기화
        currentUser = users.stream().filter(u -> name.equals(u.getName())).findAny().orElse(null);

        if (currentUser != null) {
            System.out.println("❗ 이미 등록된 이름입니다. 기존 유저를 불러옵니다.");
            return;
        }

        String gender;
        while (true) {
            System.out.print("성별 입력(m 또는 f로 입력하세요): ");
            gender = TextReader.readLine().trim().toLowerCase();
            if (gender.equals("m") || gender.equals("f")) {
                break;
            } else {
                System.out.println("❌ 잘못된 입력입니다. 'm' 또는 'f'로 입력해주세요.");
            }
        }

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
                new TypingTextWriter(),
                currentUser,
                new CrosswordGame(),
                new CodingTest(),
                new QuestionRepository()
        );
        int result = process.run();
        UserFileManager.writeUsers(users);
        return result;
    }

    static void runService() {
        while (true) {
            System.out.println("\n[📝 1. 활동] [💼 2. 채용] [📊 3. 사용자 정보(스탯 확인 가능)]");
            System.out.print("📋 메뉴를 선택하세요: ");
            try {
                int serviceChoice = Integer.parseInt(TextReader.readLine());
                switch (serviceChoice) {
                    case 1:
                        if (currentUserIsNull()) return;
                        ActivityService.runActivity(currentUser, sc);
                        break;
                    case 2:
                        if (runProcess() == 0) {
                            return;
                        }
                        break;
                    case 3:
                        if (currentUserIsNull()) return;
                        currentUser.showStats();
                        break;
                    default:
                        System.out.println("⚠️ 잘못된 입력입니다.");
                }
            }
            catch (NumberFormatException e) {
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

    // 랭킹 함수
    public static void showUserRanking(List<User> users) {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String GREEN = "\u001B[32m";
        final String YELLOW = "\u001B[33m";
        final String RED = "\u001B[31m";

        List<User> successfulUsers = users.stream()
                .filter(User::isSuccess)
                .sorted(Comparator
                        .comparingInt(User::getTotalStats).reversed()
                        .thenComparingInt(User::getFailCount)) // 실패 횟수 적을수록 우선
                .toList();

        if (successfulUsers.isEmpty()) {
            System.out.println(GREEN + "✅ 아직 합격한 유저가 없습니다." + RESET);
            return;
        }

        int width = 50;
        System.out.println(CYAN + "╔" + "═".repeat(width - 2) + "╗" + RESET);
        printCenter(width, CYAN + "🎖️ [합격 유저 순위 TOP " + successfulUsers.size() + "] 🎖️" + RESET);

        int rank = 1;
        for (User user : successfulUsers) {
            String medal = switch (rank) {
                case 1 -> "🥇";
                case 2 -> "🥈";
                case 3 -> "🥉";
                default -> rank + "위";
            };

            String line = medal + "  " + GREEN + user.getName() + RESET +
                    " | 총합 스탯: " + YELLOW + user.getTotalStats() + RESET +
                    " | 실패 횟수: " + RED + user.getFailCount() + RESET;

            printCenter(width, line);
            rank++;
        }

        System.out.println(CYAN + "╚" + "═".repeat(width - 2) + "╝" + RESET);
    }

    private static void printCenter(int width, String content) {
        int pad = (width - visualLength(content)) / 2;
        System.out.println(" ".repeat(Math.max(0, pad)) + content);
    }

    private static String stripAnsi(String s) {
        return s.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    private static int visualLength(String s) {
        String noAnsi = stripAnsi(s);
        int len = 0;
        for (int i = 0; i < noAnsi.length(); i++) {
            char ch = noAnsi.charAt(i);
            if (Character.UnicodeScript.of(ch).name().matches("HAN|HANGUL|HIRAGANA|KATAKANA") ||
                    Character.UnicodeBlock.of(ch).toString().contains("EMOJI")) {
                len += 2;
            } else {
                len += 1;
            }
        }
        return len;
    }

}