package com.stumeet.server.study.domain;

import lombok.Getter;

@Getter
public enum StudyField {
    LANGUAGE("어학"),
    EMPLOYMENT("취업"),
    CERTIFICATION("자격증"),
    CIVIL_SERVICE("고시/공무원"),
    HOBBY_EDUCATION("취미/교양"),
    PROGRAMMING("프로그래밍"),
    FINANCE_ECONOMICS("재테크/경제"),
    COLLEGE_ENTRANCE("수능"),
    READING("독서"),
    AUTONOMOUS("자율")
    ;

    private final String name;

    StudyField(String name) {
        this.name = name;
    }
}
