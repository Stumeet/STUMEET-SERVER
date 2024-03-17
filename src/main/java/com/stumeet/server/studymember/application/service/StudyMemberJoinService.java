package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberValidUseCase;
import com.stumeet.server.study.application.port.in.StudyValidUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import com.stumeet.server.studymember.application.port.in.mapper.StudyMemberUseCaseMapper;
import com.stumeet.server.studymember.application.port.out.StudyMemberJoinPort;
import com.stumeet.server.studymember.domain.StudyMember;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class StudyMemberJoinService implements StudyMemberJoinUseCase {

    private final MemberValidUseCase memberValidUseCase;
    private final StudyValidUseCase studyValidUseCase;
    private final StudyMemberUseCaseMapper studyMemberUseCaseMapper;
    private final StudyMemberJoinPort studyMemberJoinPort;

    @Override
    public void join(StudyMemberJoinCommand command) {
        memberValidUseCase.checkById(command.memberId());
        studyValidUseCase.checkById(command.studyId());
        StudyMember studyMember = studyMemberUseCaseMapper.toDomain(command);
        studyMemberJoinPort.join(studyMember);
    }
}
