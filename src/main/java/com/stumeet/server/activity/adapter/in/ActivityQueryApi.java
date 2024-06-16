package com.stumeet.server.activity.adapter.in;

import java.time.LocalDateTime;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponses;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponses;
import com.stumeet.server.activity.application.port.in.ActivityQueryUseCase;
import com.stumeet.server.activity.application.port.in.query.ActivityListBriefQuery;
import com.stumeet.server.activity.application.port.in.query.ActivityListDetailedQuery;
import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActivityQueryApi {

	private final ActivityQueryUseCase activityQueryUseCase;

	@GetMapping("/studies/{studyId}/activities/{activityId}")
	public ResponseEntity<ApiResponse<ActivityDetailResponse>> getById(
		@PathVariable Long studyId,
		@PathVariable Long activityId,
		@AuthenticationPrincipal LoginMember member
	) {
		ActivityDetailResponse response = activityQueryUseCase.getById(studyId, activityId, member.getId());
		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping("/studies/activities/detail")
	public ResponseEntity<ApiResponse<ActivityListDetailedPageResponses>> getDetailsByCondition(
		@AuthenticationPrincipal LoginMember member,
		@RequestParam Integer size,
		@RequestParam Integer page,
		@RequestParam(required = false) Boolean isNotice,
		@RequestParam(required = false) Long studyId,
		@RequestParam(required = false) String category
	) {
		ActivityListDetailedQuery query = ActivityListDetailedQuery.builder()
			.size(size)
			.page(page)
			.isNotice(isNotice)
			.studyId(studyId)
			.memberId(member.getId())
			.categoryName(category)
			.build();
		ActivityListDetailedPageResponses response = activityQueryUseCase.getDetails(query);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
	}

	@GetMapping("/studies/activities/brief")
	public ResponseEntity<ApiResponse<ActivityListBriefResponses>> getBriefsByCondition(
		@AuthenticationPrincipal LoginMember member,
		@RequestParam(required = false) Integer size,
		@RequestParam(required = false) Integer page,
		@RequestParam(required = false) Boolean isNotice,
		@RequestParam(required = false) Long studyId,
		@RequestParam(required = false) String category,
		@RequestParam(required = false) LocalDateTime fromDate,
		@RequestParam(required = false) LocalDateTime toDate
	) {
		ActivityListBriefQuery query = ActivityListBriefQuery.builder()
			.size(size)
			.page(page)
			.isNotice(isNotice)
			.studyId(studyId)
			.memberId(member.getId())
			.categoryName(category)
			.startDate(fromDate)
			.endDate(toDate)
			.build();
		ActivityListBriefResponses response = activityQueryUseCase.getBriefs(query);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(SuccessCode.GET_SUCCESS, response));
	}
}
