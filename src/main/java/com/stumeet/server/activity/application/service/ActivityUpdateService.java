package com.stumeet.server.activity.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.in.ActivityAuthorityValidationUseCase;
import com.stumeet.server.activity.application.port.in.ActivityUpdateUseCase;
import com.stumeet.server.activity.application.port.in.command.ActivityUpdateCommand;
import com.stumeet.server.activity.application.port.in.mapper.ActivityImageUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityUseCaseMapper;
import com.stumeet.server.activity.application.port.out.ActivityImageCommandPort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCommandPort;
import com.stumeet.server.activity.application.port.out.ActivitySavePort;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.application.service.model.ActivitySource;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityImage;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@Transactional
@UseCase
@RequiredArgsConstructor
public class ActivityUpdateService implements ActivityUpdateUseCase {

    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final ActivityAuthorityValidationUseCase activityAuthorityValidationUseCase;

    private final ActivityQueryPort activityQueryPort;
    private final ActivitySavePort activitySavePort;
    private final ActivityImageCommandPort activityImageCommandPort;
    private final ActivityParticipantCommandPort activityParticipantCommandPort;

    private final ActivityUseCaseMapper activityUseCaseMapper;
    private final ActivityImageUseCaseMapper activityImageUseCaseMapper;
    private final ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper;

    @Override
    public void update(Long memberId, Long studyId, Long activityId, ActivityUpdateCommand command) {
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);
        activityAuthorityValidationUseCase.checkDeleteAuthority(studyId, memberId, activityId);

        Activity exist = activityQueryPort.getById(activityId);
        ActivitySource source = activityUseCaseMapper.toUpdateSource(exist, command);
        Activity updated = exist.update(memberId, source);
        activitySavePort.save(updated);

        List<ActivityParticipant> participants = activityParticipantUseCaseMapper.toDomains(command.participants(), updated);
        activityParticipantCommandPort.update(activityId, participants);

        List<ActivityImage> images = activityImageUseCaseMapper.toDomains(command.images(), updated);
        activityImageCommandPort.update(activityId, images);
    }
}
