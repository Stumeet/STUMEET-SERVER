package com.stumeet.server.activity.application.port.in.mapper;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityImageResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponses;
import com.stumeet.server.activity.adapter.in.response.ActivityParticipantSimpleResponse;
import com.stumeet.server.common.model.PageInfoResponse;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.application.port.in.command.ActivityUpdateCommand;
import com.stumeet.server.activity.application.service.model.ActivitySource;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ActivityUseCaseMapper {

    private final ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper;

    public ActivitySource toCreateSource(Long studyId, ActivityCreateCommand command, Long id) {
        return ActivitySource.builder()
                .id(null)
                .studyId(studyId)
                .author(ActivitySource.ActivityMemberCreateSource.builder()
                        .id(id)
                        .build())
                .category(ActivityCategory.getByName(command.category()))
                .title(command.title())
                .content(command.content())
                .location(command.location())
                .link(command.link())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .isNotice(command.isNotice())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ActivitySource toUpdateSource(Activity exist, ActivityUpdateCommand command) {
        return ActivitySource.builder()
                .id(exist.getId())
                .studyId(exist.getStudy().getId())
                .author(ActivitySource.ActivityMemberCreateSource.builder()
                        .id(exist.getAuthor().getId())
                        .build())
                .category(ActivityCategory.getByName(command.category()))
                .title(command.title())
                .content(command.content())
                .location(command.location())
                .link(command.link())
                .startDate(command.startDate())
                .endDate(command.endDate())
                .isNotice(command.isNotice())
                .createdAt(exist.getCreatedAt())
                .build();
    }

    public ActivityDetailResponse toDetailResponse(
            Activity activity,
            List<ActivityImageResponse> activityImages,
            ActivityParticipantSimpleResponse author,
            List<ActivityParticipantSimpleResponse> participants,
            String status,
            boolean isAuthor,
            boolean isAdmin
    ) {
        return ActivityDetailResponse.builder()
                .id(activity.getId())
                .category(activity.getCategory().name())
                .title(activity.getTitle())
                .content(activity.getContent())
                .imageUrl(activityImages)
                .author(author)
                .participants(participants)
                .status(status)
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .location(activity.getLocation())
                .link(activity.getLink())
                .createdAt(activity.getCreatedAt())
                .isAuthor(isAuthor)
                .isAdmin(isAdmin)
                .build();
    }

    private ActivityListDetailedPageResponse toListDetailedPageResponse(Activity activity) {
        return ActivityListDetailedPageResponse.builder()
                .id(activity.getId())
                .category(activity.getCategory().name())
                .title(activity.getTitle())
                .content(activity.getContent())
                .startDate(activity.getStartDate())
                .endDate(activity.getEndDate())
                .location(activity.getLocation())
                .author(activityParticipantUseCaseMapper.toResponse(activity.getAuthor()))
                .createdAt(activity.getCreatedAt())
                .build();
    }

    public ActivityListDetailedPageResponses toListDetailedPageResponses(
            Page<Activity> activities,
            PageInfoResponse pageInfoResponse
    ) {
        return ActivityListDetailedPageResponses.builder()
                .items(activities.stream()
                        .map(this::toListDetailedPageResponse)
                        .toList())
                .pageInfo(pageInfoResponse)
                .build();
    }
}