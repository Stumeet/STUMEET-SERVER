package com.stumeet.server.studymember.application.port.in.command;

import lombok.Builder;

@Builder
public record StudyMemberJoinCommand(

        Long memberId,

        Long studyId,

        boolean isAdmin
) {
}
