package com.stumeet.server.stub;

import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.domain.JoinMember;
import com.stumeet.server.studymember.domain.JoinStudy;
import com.stumeet.server.studymember.domain.StudyMember;

public class StudyMemberStub {

    private StudyMemberStub() {

    }

    public static StudyMemberJoinCommand getJoinCommand() {
        return StudyMemberJoinCommand.builder()
                .studyId(1L)
                .memberId(1L)
                .isAdmin(true)
                .build();
    }

    public static StudyMemberJoinCommand getInvalidJoinCommandByMemberId() {
        return StudyMemberJoinCommand.builder()
                .studyId(1L)
                .memberId(0L)
                .isAdmin(true)
                .build();
    }

    public static StudyMemberJoinCommand getInvalidJoinCommandByStudyId() {
        return StudyMemberJoinCommand.builder()
                .studyId(0L)
                .memberId(1L)
                .isAdmin(true)
                .build();
    }

    public static StudyMember getStudyMember() {
        return StudyMember.builder()
                .id(1L)
                .study(JoinStudy.builder().id(1L).build())
                .member(JoinMember.builder().id(1L).build())
                .isSentGrape(false)
                .isAdmin(true)
                .build();
    }
}
