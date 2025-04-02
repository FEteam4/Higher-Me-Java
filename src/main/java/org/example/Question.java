package org.example;

import java.util.List;

public class Question {

    String name;
    List<String> options;
    int answer;

    public Question(String name, List<String> options, int answer) {
        this.name = name;
        this.options = options;
        this.answer = answer;
    }
}
