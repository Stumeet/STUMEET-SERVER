package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.application.port.in.ActivityCreateUseCase;
import com.stumeet.server.activity.application.service.model.ActivityCreateSource;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.application.port.in.mapper.ActivityImageUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityUseCaseMapper;
import com.stumeet.server.activity.application.port.out.ActivityCreatePort;
import com.stumeet.server.activity.application.port.out.ActivityImageCreatePort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCreatePort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityImage;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional
@RequiredArgsConstructor
public class ActivityCreateService implements ActivityCreateUseCase {

    private final ActivityCreatePort activityCreatePort;
    private final ActivityImageCreatePort activityImageCreatePort;
    private final ActivityParticipantCreatePort activityParticipantPort;

    private final StudyMemberValidationUseCase studyMemberValidationUseCase;

    private final ActivityUseCaseMapper activityUseCaseMapper;
    private final ActivityImageUseCaseMapper activityImageUseCaseMapper;
    private final ActivityParticipantUseCaseMapper activityMemberUseCaseMapper;

    @Override
    public void create(Long studyId, ActivityCreateCommand command, Long memberId) {
        studyMemberValidationUseCase.checkAdmin(studyId, memberId);

        ActivityCreateSource constructCommand = activityUseCaseMapper.toConstructCommand(studyId, command, memberId);
        Activity activity = command.category().create(constructCommand);
        Activity createdActivity = activityCreatePort.create(activity);

        List<ActivityImage> images = activityImageUseCaseMapper.toDomains(command.images(), createdActivity);
        activityImageCreatePort.create(images);

        List<ActivityParticipant> participants = activityMemberUseCaseMapper.toDomains(command.participants(), createdActivity);
        activityParticipantPort.create(participants);
    }
}
