package com.stumeet.server.activity.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.in.ParticipantStatusUpdateUseCase;
import com.stumeet.server.activity.application.port.in.command.ParticipantStatusUpdateCommand;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCommandPort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantQueryPort;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ParticipantStatusUpdateService implements ParticipantStatusUpdateUseCase {

    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;

    private final ActivityQueryPort activityQueryPort;
    private final ActivityParticipantQueryPort activityParticipantQueryPort;
    private final ActivityParticipantCommandPort activityParticipantCommandPort;

    @Override
    public void updateStatus(ParticipantStatusUpdateCommand command) {
        studyValidationUseCase.checkById(command.studyId());
        studyMemberValidationUseCase.checkAdmin(command.studyId(), command.adminId());
        studyMemberValidationUseCase.checkStudyJoinMember(command.studyId(), command.memberId());

        Activity activity = activityQueryPort.getByStudyIdAndId(command.studyId(), command.activityId());
        activity.getCategory().validateStatus(command.status());

        ActivityParticipant participant = activityParticipantQueryPort.findByActivityIdAndMemberIdAndId(command.activityId(), command.memberId(), command.participantId());
        ActivityParticipant updated = participant.update(command.status());

        activityParticipantCommandPort.update(updated);
    }
}
