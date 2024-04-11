package com.stumeet.server.stub;

import com.stumeet.server.member.domain.Member;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.application.port.in.response.SimpleStudyMemberResponse;
import com.stumeet.server.studymember.domain.JoinMember;
import com.stumeet.server.studymember.domain.JoinStudy;
import com.stumeet.server.studymember.domain.StudyMember;

public class StudyMemberStub {

    private StudyMemberStub() {

    }

    public static StudyMemberJoinCommand getJoinCommand() {
        return StudyMemberJoinCommand.builder()
                .studyId(1L)
                .memberId(2L)
                .isAdmin(true)
                .build();
    }

    public static StudyMemberJoinCommand getAlreadyJoinCommand() {
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

    public static SimpleStudyMemberResponse getSimpleStudyMemberResponse() {
        Member member = MemberStub.getMember();
        return SimpleStudyMemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .image(member.getImage())
                .region(member.getRegion())
                .profession(member.getProfession().getName())
                .isAdmin(true)
                .build();
    }

    public static Long getStudyAdminId() {
        return 1L;
    }
}
