package com.stumeet.server.activity.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.in.ActivityAuthorityValidationUseCase;
import com.stumeet.server.activity.application.port.in.ActivityDeleteUseCase;
import com.stumeet.server.activity.application.port.in.command.ActivityDeleteCommand;
import com.stumeet.server.activity.application.port.out.ActivityDeletePort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCommandPort;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.ActivityImageDeleteUseCase;
import com.stumeet.server.study.application.port.in.ActivityParticipantDeleteUseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ActivityDeleteService implements ActivityDeleteUseCase {

    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final StudyValidationUseCase studyValidationUseCase;
    private final ActivityAuthorityValidationUseCase activityAuthorityValidationUseCase;

    private final ActivityImageDeleteUseCase activityImageDeleteUseCase;
    private final ActivityParticipantDeleteUseCase activityParticipantDeleteUseCase;

    private final ActivityDeletePort activityDeletePort;

    @Override
    public void delete(ActivityDeleteCommand command) {
        studyValidationUseCase.checkById(command.studyId());
        studyMemberValidationUseCase.checkStudyJoinMember(command.studyId(), command.memberId());
        activityAuthorityValidationUseCase.checkDeleteAuthority(command.studyId(), command.memberId(), command.activityId());

        activityImageDeleteUseCase.deleteByActivityId(command.studyId(), command.activityId());
        activityParticipantDeleteUseCase.deleteByActivityId(command.activityId());
        activityDeletePort.deleteById(command.activityId());
    }
}
