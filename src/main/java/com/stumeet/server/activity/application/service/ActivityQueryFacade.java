package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.adapter.in.response.ActivityDetailResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponse;
import com.stumeet.server.activity.adapter.in.response.ActivityListBriefResponses;
import com.stumeet.server.activity.adapter.in.response.ActivityListDetailedPageResponses;
import com.stumeet.server.activity.application.port.in.ActivityImageQuery;
import com.stumeet.server.activity.application.port.in.ActivityParticipantQuery;
import com.stumeet.server.activity.application.port.in.ActivityQuery;
import com.stumeet.server.activity.application.port.in.ActivityQueryUseCase;
import com.stumeet.server.activity.application.port.in.mapper.ActivityImageUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityParticipantUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.ActivityUseCaseMapper;
import com.stumeet.server.activity.application.port.in.mapper.PageInfoUseCaseMapper;
import com.stumeet.server.activity.application.port.in.query.ActivityListBriefQuery;
import com.stumeet.server.activity.application.port.in.query.ActivityListDetailedQuery;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityImage;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.activity.domain.model.ActivitySort;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ActivityQueryFacade implements ActivityQueryUseCase {

    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;

    private final ActivityQuery activityQuery;
    private final ActivityImageQuery activityImageQuery;
    private final ActivityParticipantQuery activityParticipantQuery;

    private final StudyMemberValidationPort studyMemberValidationPort;

    private final ActivityUseCaseMapper activityUseCaseMapper;
    private final ActivityImageUseCaseMapper activityImageUseCaseMapper;
    private final ActivityParticipantUseCaseMapper activityParticipantUseCaseMapper;
    private final PageInfoUseCaseMapper pageInfoUseCaseMapper;

    @Override
    public ActivityDetailResponse getById(Long studyId, Long activityId, Long memberId) {
        studyValidationUseCase.checkById(studyId);
        studyMemberValidationUseCase.checkStudyJoinMember(studyId, memberId);

        Activity activity = activityQuery.getById(activityId);
        List<ActivityImage> activityImages = activityImageQuery.findAllByActivityId(activityId);
        List<ActivityParticipant> participants = activityParticipantQuery.findAllByActivityId(activityId);

        ActivityParticipant me = participants.stream()
                .filter(participant -> participant.getMember().getId().equals(memberId))
                .findAny()
                .orElseGet(ActivityParticipant::makeNotJoinedMember);

        return activityUseCaseMapper.toDetailResponse(
                activity,
                activityImageUseCaseMapper.toResponses(activityImages),
                activityParticipantUseCaseMapper.toResponse(activity.getAuthor()),
                activityParticipantUseCaseMapper.toResponses(participants),
                me.getStatus().getDescription(),
                activity.isAuthor(memberId),
                studyMemberValidationPort.isAdmin(studyId, memberId)
        );
    }

    @Override
    public ActivityListDetailedPageResponses getDetails(ActivityListDetailedQuery query) {
        if (query.getStudyId() != null) {
            studyValidationUseCase.checkById(query.getStudyId());
        }

        Page<Activity> activities = activityQuery.getDetailsByCondition(
                PageRequest.of(query.getPage(), query.getSize()),
                query.getIsNotice(),
                query.getStudyId(),
                query.getCategories(),
                query.getSort());

        return activityUseCaseMapper
                .toListDetailedPageResponses(activities, pageInfoUseCaseMapper.toPageInfoResponse(activities));
    }

    @Override
    public ActivityListBriefResponses getBriefs(ActivityListBriefQuery query) {
        if (query.getStudyId() != null) {
            studyValidationUseCase.checkById(query.getStudyId());
        }

        if (query.isPaginationRequest()) {
            Page<ActivityListBriefResponse> activities = activityQuery.getPaginatedBriefsByCondition(
                    PageRequest.of(query.getPage(), query.getSize()),
                    query.getIsNotice(),
                    query.getMemberId(),
                    query.getStudyId(),
                    query.getCategories(),
                    query.getFromDate(),
                    query.getToDate(),
                    query.getSort());

            return ActivityListBriefResponses.builder()
                    .items(activities.toList())
                    .pageInfo(pageInfoUseCaseMapper.toPageInfoResponse(activities))
                    .build();
        } else {
            List<ActivityListBriefResponse> activities = activityQuery.getBriefsByCondition(
                    query.getIsNotice(),
                    query.getMemberId(),
                    query.getStudyId(),
                    query.getCategories(),
                    query.getFromDate(),
                    query.getToDate(),
                    query.getSort());

            return ActivityListBriefResponses.builder()
                    .items(activities)
                    .pageInfo(null)
                    .build();
        }
    }
}
