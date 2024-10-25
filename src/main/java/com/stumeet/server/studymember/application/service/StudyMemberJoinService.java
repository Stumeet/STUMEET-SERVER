package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.notification.application.port.in.SubscribeTopicUseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
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
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final SubscribeTopicUseCase subscribeTopicUseCase;

    private final StudyMemberJoinPort studyMemberJoinPort;

    private final StudyMemberUseCaseMapper studyMemberUseCaseMapper;

    @Override
    public void join(StudyMemberJoinCommand command) {
        memberValidationUseCase.checkById(command.memberId());
        studyValidationUseCase.checkById(command.studyId());
        studyMemberValidationUseCase.checkAlreadyStudyJoinMember(command.studyId(), command.memberId());

        StudyMember studyMember = studyMemberUseCaseMapper.toDomain(command);

        // todo: headcount 검사 및 인원 수 증가 로직 추가 필요
        studyMemberJoinPort.join(studyMember);

        subscribeTopicUseCase.subscribeStudyNoticeTopic(command.memberId(), command.studyId());
    }
}
