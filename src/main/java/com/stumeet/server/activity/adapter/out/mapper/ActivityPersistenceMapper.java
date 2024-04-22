package com.stumeet.server.activity.adapter.out.mapper;

import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.adapter.out.model.ActivityLinkedStudyJpaEntity;
import com.stumeet.server.activity.adapter.out.model.ActivityMemberJpaEntity;
import com.stumeet.server.activity.application.port.in.command.ActivityConstructCommand;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.Meet;
import org.springframework.stereotype.Component;

@Component
public class ActivityPersistenceMapper {

    public Activity toDomain(ActivityJpaEntity entity) {
        ActivityConstructCommand request = ActivityConstructCommand.builder()
                .id(entity.getId())
                .author(ActivityConstructCommand.ActivityMemberConstructCommand.builder()
                        .id(entity.getAuthor().getId())
                        .name(entity.getAuthor().getName())
                        .image(entity.getAuthor().getImage())
                        .build())
                .studyId(entity.getStudy().getId())
                .category(entity.getCategory())
                .title(entity.getTitle())
                .content(entity.getContent())
                .isNotice(entity.isNotice())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .createdAt(entity.getCreatedAt())
                .location(entity.getLocation())
                .build();

        return ActivityCategory.createByCategory(entity.getCategory(), request);
    }

    public ActivityJpaEntity toEntity(Activity domain) {
        return ActivityJpaEntity.builder()
                .id(domain.getId())
                .author(ActivityMemberJpaEntity.builder()
                        .id(domain.getAuthor().getId())
                        .name(domain.getAuthor().getName())
                        .image(domain.getAuthor().getImage())
                        .build())
                .study(ActivityLinkedStudyJpaEntity.builder()
                        .id(domain.getStudy().getId())
                        .build())
                .category(domain.getCategory())
                .title(domain.getTitle())
                .content(domain.getContent())
                .isNotice(domain.isNotice())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .location(domain instanceof Meet meet ? meet.getLocation() : null)
                .build();
    }
}
