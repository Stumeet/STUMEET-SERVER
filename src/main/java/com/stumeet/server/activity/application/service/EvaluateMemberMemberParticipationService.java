package com.stumeet.server.activity.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.in.EvaluateMemberAchievementUseCase;
import com.stumeet.server.activity.application.port.out.ActivityParticipantQueryPort;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.activity.domain.model.CommonStatus;
import com.stumeet.server.common.annotation.UseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvaluateMemberMemberParticipationService implements EvaluateMemberAchievementUseCase {
    private static final int PERCENTAGE_MULTIPLIER = 100;

    private final ActivityParticipantQueryPort activityParticipantQueryPort;

    @Override
    public Integer getMemberAchievement(Long studyId, Long memberId) {
        List<ActivityParticipant> activityParticipants =
                activityParticipantQueryPort.findMemberParticipation(studyId, memberId);

        return calculateParticipationPercentage(activityParticipants);
    }

    private Integer calculateParticipationPercentage(List<ActivityParticipant> participants) {
        List<ActivityParticipant> joinedActivities = getValidJoinedActivities(participants);
        long totalActivities = joinedActivities.size();

        if (totalActivities == 0) {
            return 0;
        }

        long achievedActivities = joinedActivities.stream()
                .filter(ActivityParticipant::isAchieved)
                .count();

        double participation = (double)achievedActivities / totalActivities * PERCENTAGE_MULTIPLIER;
        return (int)participation;
    }

    private List<ActivityParticipant> getValidJoinedActivities(List<ActivityParticipant> participants) {
        return participants.stream()
                .filter(participant -> isValidCategory(participant) && hasJoined(participant))
                .toList();
    }

    private boolean isValidCategory(ActivityParticipant participant) {
        return participant.getActivity().getCategory().equals(ActivityCategory.ASSIGNMENT)
                || participant.getActivity().getCategory().equals(ActivityCategory.MEET);
    }

    private boolean hasJoined(ActivityParticipant participant) {
        return !participant.getStatus().equals(CommonStatus.NOT_JOINED);
    }
}
