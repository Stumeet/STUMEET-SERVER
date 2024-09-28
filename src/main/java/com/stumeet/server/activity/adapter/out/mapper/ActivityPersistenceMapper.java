package com.stumeet.server.activity.adapter.out.mapper;

import java.util.List;

import com.stumeet.server.activity.adapter.out.model.ActivityJpaEntity;
import com.stumeet.server.activity.adapter.out.model.ActivityLinkedStudyJpaEntity;
import com.stumeet.server.activity.adapter.out.model.ActivityMemberJpaEntity;
import com.stumeet.server.activity.application.service.model.ActivitySource;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.Meet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

@Component
public class ActivityPersistenceMapper {

    public Activity toDomain(ActivityJpaEntity entity) {
        ActivitySource request = ActivitySource.builder()
                .id(entity.getId())
                .author(ActivitySource.ActivityMemberCreateSource.builder()
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
                .location(entity.getLocation())
                .link(entity.getLink())
                .createdAt(entity.getCreatedAt())
                .location(entity.getLocation())
                .build();

        return entity.getCategory().create(request);
    }

    public Page<Activity> toDomainPages(Page<ActivityJpaEntity> entities) {
        List<Activity> domains = entities.stream()
                .map(this::toDomain)
                .toList();

        return new PageImpl<>(domains, entities.getPageable(), entities.getTotalElements());
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
                .link(domain.getLink())
                .build();
    }
}
