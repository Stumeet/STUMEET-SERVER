package com.stumeet.server.activity.domain.model;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityParticipant {

    private Long id;

    private Activity activity;

    private ActivityMember member;

    private ActivityStatus status;
}
