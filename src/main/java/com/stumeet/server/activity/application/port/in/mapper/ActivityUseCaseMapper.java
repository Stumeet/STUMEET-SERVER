package com.stumeet.server.activity.application.port.in.mapper;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityImageResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponses;
import com.stumeet.server.activity.adapter.in.response.ActivityParticipantSimpleResponse;
import com.stumeet.server.activity.adapter.in.response.PageInfoResponse;
import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.activity.application.service.model.ActivityCreateSource;
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

	public ActivityCreateSource toSource(Long studyId, ActivityCreateCommand command, Long id) {
		return ActivityCreateSource.builder()
				.id(null)
				.studyId(studyId)
				.author(ActivityCreateSource.ActivityMemberCreateSource.builder()
						.id(id)
						.build())
				.category(ActivityCategory.getByName(command.category()))
				.title(command.title())
				.content(command.content())
				.isNotice(command.isNotice())
				.startDate(command.startDate())
				.endDate(command.endDate())
				.location(command.location())
				.createdAt(LocalDateTime.now())
				.build();
	}

	public ActivityDetailResponse toDetailResponse(
			Activity activity,
			List<ActivityImageResponse> activityImages,
			ActivityParticipantSimpleResponse author,
			List<ActivityParticipantSimpleResponse> participants,
			String status
	) {
		return ActivityDetailResponse.builder()
				.id(activity.getId())
				.title(activity.getTitle())
				.content(activity.getContent())
				.imageUrl(activityImages)
				.author(author)
				.participants(participants)
				.status(status)
				.startDate(activity.getStartDate())
				.endDate(activity.getEndDate())
				.location(activity.getLocation())
				.createdAt(activity.getCreatedAt())
				.build();
	}

	public ActivityListDetailedPageResponse toListDetailResponse(Activity activity) {
		return ActivityListDetailedPageResponse.builder()
				.id(activity.getId())
				.category(activity.getCategory())
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
						.map(this::toListDetailResponse)
						.toList())
				.pageInfo(pageInfoResponse)
				.build();
	}
}