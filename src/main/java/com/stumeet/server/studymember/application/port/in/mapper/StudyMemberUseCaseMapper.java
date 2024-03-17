package com.stumeet.server.studymember.application.port.in.mapper;

import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.domain.StudyMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudyMemberUseCaseMapper {

    private final JoinMemberUseCaseMapper joinMemberUseCaseMapper;
    private final JoinStudyUseCaseMapper joinStudyUseCaseMapper;

    public StudyMember toDomain(StudyMemberJoinCommand command) {
        return StudyMember.builder()
                .study(joinStudyUseCaseMapper.toDomain(command.studyId()))
                .member(joinMemberUseCaseMapper.toDomain(command.memberId()))
                .isAdmin(command.isAdmin())
                .isSentGrape(false)
                .build();
    }
}
