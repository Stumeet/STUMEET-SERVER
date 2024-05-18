package com.stumeet.server.activity.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityParticipant {

    private Long id;

    private Activity activity;

    private ActivityMember member;

    private ActivityStatus status;

    public static ActivityParticipant makeAnonymous() {
        return ActivityParticipant.builder()
                .build();
    }
}
