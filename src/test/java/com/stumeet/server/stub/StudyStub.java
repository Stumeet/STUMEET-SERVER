package com.stumeet.server.stub;

public class StudyStub {
    private StudyStub() {

    }

    public static Long getStudyId() {
        return 1L;
    }

    public static Long getInvalidStudyId() {
        return 0L;
    }
}
