# 💼 Higher Me!

> **당신의 취업 여정을 시뮬레이션해보세요!**  
> `Higher Me!`는 Java로 개발된 텍스트 기반 CLI 취업 시뮬레이션 게임입니다.  
> 다양한 활동과 미니게임을 통해 스펙을 올리고, 채용 전형을 돌파하세요!
> 앞으로의 행운을 빕니다!🍀

---

## 🕹️ 실행 방법

### ✅ 1. Git으로 클론 후 실행 (터미널에서)

```bash
git clone https://github.com/FEteam4/Higher-Me-Java.git
cd Higher-Me-Java
./gradlew shadowJar
java -jar build/libs/HigherMe-1.0-SNAPSHOT-all.jar
```

> 💡 macOS/Linux에서는 `./gradlew`, Windows에서는 `gradlew.bat` 사용

---

### ✅ 2. IntelliJ IDEA에서 실행

1. `IntelliJ` → `Open` → `Higher-Me-Java` 폴더 선택
2. Gradle 프로젝트로 인식되면 자동 설정됨
3. `src/main/java/org.example.Main` 파일 열기
4. `Main` 클래스에서 ▶ 버튼 눌러 실행

---

## 🌟 게임 주요 기능

### 📢 중요
- 🕒 텍스트가 모두 출력될 때까지 기다려 주세요.  
- 텍스트가 출력되는 동안 Enter 키를 누르지 말아주세요.
- ⛔ 중간에 Enter 키를 누르면 출력이 중단될 수 있습니다.
- 사용자 이름은 중복이 불가능합니다.

### 🎯 사용자 등록
- 이름, 성별을 입력해 나만의 캐릭터 생성
- 초기 스탯 자동 설정

### 🧠 활동 선택 (최대 3회)
- **코딩테스트**, **자격증**, **동아리**, **인턴**, **운동**, **기타** 
- 각 활동마다 **2개의 선택지**, 확률 기반 성공/실패
- 스탯 변화: 개발 능력, 코테 실력, CS 지식, PT 능력, 외국어, 건강 

### 🧩 미니게임
- 각 전형 전, 미니게임 진행
- **낱말 퀴즈, 블록 코딩, 4지선다 퀴즈** 포함

### 🧪 채용 전형 시뮬레이션
- 실제 취업 프로세스를 반영:
    1. **서류 전형** (낱말 퀴즈)
    2. **필기 전형** (블록 코딩 게임)
    3. **실무 면접 전형** (4지선다 퀴즈)
    4. **임원 면접 전형** (4지선다 퀴즈)
- 각 전형별 스탯 기준치를 기반으로 성공/실패 결정

### 📊 결과 & 랭킹
- 최종 합격 or 탈락 여부 확인
- 실패 횟수와 스탯 합계 기준으로 **랭킹 출력**
- 모든 유저는 JSON 파일로 저장됨

---

## 🛠 기술 스택

- **Java 17+**
- **Gradle** 빌드 도구
- **CLI (콘솔 입출력)** 기반

---

## 📁 프로젝트 구조

```
├── 📦src
│   └── 📂main
│       ├── 📂java
│       │   └── 📂org
│       │       └── 📂example
│       │           ├── ActivityLoader.java
│       │           ├── ActivityOption.java
│       │           ├── ActivityService.java
│       │           ├── CrosswordGame.java
│       │           ├── Main.java
│       │           ├── Question.java
│       │           ├── QuestionRepository.java
│       │           ├── RecruitingProcess.java
│       │           ├── Story.java
│       │           ├── User.java
│       │           ├── UserFileManager.java
│       │           ├── 📂coding
│       │           │   ├── CodingTest.java
│       │           │   ├── 📂controller
│       │           │   │   └── GameController.java
│       │           │   ├── 📂domain
│       │           │   │   ├── Blank.java
│       │           │   │   ├── Block.java
│       │           │   │   ├── BlockManager.java
│       │           │   │   └── GameBoard.java
│       │           │   └── 📂view
│       │           │       ├── InputView.java
│       │           │       └── OutputView.java
│       │           └── 📂io
│       │               ├── LineByLineTextWriter.java
│       │               ├── TextReader.java
│       │               ├── TextWriter.java
│       │               └── TypingTextWriter.java
│       └── 📂resources
│           └── 📂activity
│               ├── certification.json
│               ├── codingtest.json
│               ├── exercise.json
│               ├── intern.json
│               ├── others.json
│               └── schoolClub.json
└── users.json
```

