package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
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

    private final MemberValidationUseCase memberValidationUseCase;
    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberUseCaseMapper studyMemberUseCaseMapper;
    private final StudyMemberJoinPort studyMemberJoinPort;

    @Override
    public void join(StudyMemberJoinCommand command) {
        memberValidationUseCase.checkById(command.memberId());
        studyValidationUseCase.checkById(command.studyId());
        StudyMember studyMember = studyMemberUseCaseMapper.toDomain(command);
        studyMemberJoinPort.join(studyMember);
    }
}
