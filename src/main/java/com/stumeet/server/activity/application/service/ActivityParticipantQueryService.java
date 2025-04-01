package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.application.port.in.ActivityParticipantQuery;
import com.stumeet.server.activity.application.port.out.ActivityParticipantQueryPort;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityParticipantQueryService implements ActivityParticipantQuery {

    private final ActivityParticipantQueryPort activityParticipantQueryPort;

    @Override
    public List<ActivityParticipant> findAllByActivityId(Long activityId) {
        return activityParticipantQueryPort.findAllByActivityId(activityId);
    }
}
