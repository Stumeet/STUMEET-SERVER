package com.stumeet.server.activity.adapter.out.mapper;

import com.stumeet.server.activity.adapter.out.model.ActivityMemberJpaEntity;
import com.stumeet.server.activity.adapter.out.model.ActivityParticipantJpaEntity;
import com.stumeet.server.activity.domain.model.ActivityMember;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityParticipantPersistenceMapper {

    private final ActivityPersistenceMapper activityPersistenceMapper;


    public ActivityParticipant toDomain(ActivityParticipantJpaEntity entity) {
        return ActivityParticipant.builder()
                .id(entity.getId())
                .activity(activityPersistenceMapper.toDomain(entity.getActivity()))
                .member(ActivityMember.builder()
                        .id(entity.getMember().getId())
                        .name(entity.getMember().getName())
                        .image(entity.getMember().getImage())
                        .build())
                .status(entity.getStatus())
                .build();
    }

    public ActivityParticipantJpaEntity toEntity(ActivityParticipant domain) {
        return ActivityParticipantJpaEntity.builder()
                .id(domain.getId())
                .activity(activityPersistenceMapper.toEntity(domain.getActivity()))
                .member(ActivityMemberJpaEntity.builder()
                        .id(domain.getMember().getId())
                        .build())
                .status(domain.getStatus())
                .build();
    }
}
