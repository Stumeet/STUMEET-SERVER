package com.stumeet.server.studymember.adapter.in.web.mapper;

import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import org.springframework.stereotype.Component;

@Component
public class StudyMemberJoinWebAdapterMapper {
    public StudyMemberJoinCommand toCommand(Long studyId, Long memberId) {
        return StudyMemberJoinCommand.builder()
                .studyId(studyId)
                .memberId(memberId)
                .isAdmin(false)
                .build();
    }
}
