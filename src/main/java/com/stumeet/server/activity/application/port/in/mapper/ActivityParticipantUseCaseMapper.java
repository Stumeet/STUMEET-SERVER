package com.stumeet.server.activity.application.port.in.mapper;

import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityMember;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityParticipantUseCaseMapper {

    public ActivityParticipant toDomain(Long participant, Activity activity) {
        return ActivityParticipant.builder()
                .activity(activity)
                .member(ActivityMember.builder()
                        .id(participant)
                        .build())
                .activity(activity)
                .status(activity.getCategory().getDefaultStatus())
                .build();
    }

    public List<ActivityParticipant> toDomains(List<Long> participants, Activity activity) {
        return participants.stream()
                .map(participant -> toDomain(participant, activity))
                .toList();
    }
}
