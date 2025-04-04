package com.stumeet.server.activity.application.port.in.mapper;

import com.stumeet.server.activity.adapter.in.response.ActivityParticipantSimpleResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityParticipantStatusResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityParticipantStatusResponses;
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

    public ActivityParticipantSimpleResponse toResponse(ActivityMember member) {
        return ActivityParticipantSimpleResponse.builder()
                .memberId(member.getId())
                .name(member.getName())
                .profileImageUrl(member.getImage())
                .build();
    }

    public List<ActivityParticipantSimpleResponse> toResponses(List<ActivityParticipant> participants) {
        return participants.stream()
                .map(participant -> toResponse(participant.getMember()))
                .toList();
    }

    public ActivityParticipantStatusResponses toStatusResponses(List<ActivityParticipant> participants) {
        List<ActivityParticipantStatusResponse> result = participants.stream()
                .map(activityParticipant -> ActivityParticipantStatusResponse.builder()
                        .id(activityParticipant.getId())
                        .name(activityParticipant.getMember().getName())
                        .profileImageUrl(activityParticipant.getMember().getImage())
                        .status(activityParticipant.getStatus().getDescription())
                        .build())
                .toList();

        return ActivityParticipantStatusResponses.builder()
                .participants(result)
                .build();
    }
}
