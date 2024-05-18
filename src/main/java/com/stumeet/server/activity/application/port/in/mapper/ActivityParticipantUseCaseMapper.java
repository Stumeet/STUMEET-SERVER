package com.stumeet.server.activity.application.port.in.mapper;

import com.stumeet.server.activity.adapter.in.response.ActivityParticipantResponse;
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

    public ActivityParticipantResponse toResponse(ActivityMember member) {
        return ActivityParticipantResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .profileImageUrl(member.getImage())
                .build();
    }

    public List<ActivityParticipantResponse> toResponses(List<ActivityParticipant> participants) {
        return participants.stream()
                .map(participant -> toResponse(participant.getMember()))
                .toList();
    }

}
