package com.stumeet.server.activity.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.activity.application.port.in.ParticipantStatusUpdateUseCase;
import com.stumeet.server.activity.application.port.in.command.ParticipantStatusUpdateCommand;
import com.stumeet.server.activity.application.port.out.ActivityParticipantCommandPort;
import com.stumeet.server.activity.application.port.out.ActivityParticipantQueryPort;
import com.stumeet.server.activity.application.port.out.ActivityQueryPort;
import com.stumeet.server.activity.domain.model.Activity;
import com.stumeet.server.activity.domain.model.ActivityCategory;
import com.stumeet.server.activity.domain.model.ActivityParticipant;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.member.application.port.in.MemberLevelUseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ParticipantStatusUpdateService implements ParticipantStatusUpdateUseCase {

    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final MemberLevelUseCase memberLevelUseCase;

    private final ActivityQueryPort activityQueryPort;
    private final ActivityParticipantQueryPort activityParticipantQueryPort;
    private final ActivityParticipantCommandPort activityParticipantCommandPort;

    @Override
    public void updateStatus(ParticipantStatusUpdateCommand command) {
        // 입력 값 검증
        studyValidationUseCase.checkById(command.studyId());
        studyMemberValidationUseCase.checkAdmin(command.studyId(), command.adminId());

        Activity activity = activityQueryPort.getByStudyIdAndId(command.studyId(), command.activityId());
        validateActivityCategory(activity);
        activity.getCategory().validateStatus(command.status());

        // 활동 참여자 상태 업데이트
        ActivityParticipant participant = activityParticipantQueryPort.findByIdAndActivityId(command.participantId(), command.activityId());
        ActivityParticipant updated = participant.update(command.status());
        activityParticipantCommandPort.update(updated);

        // TODO: 활동 성공의 경우 멤버 경험치 처리
        // TODO: 기획 결정 사안 추후 적용 예정
        // if (updated.isAchieved()) {
        //     memberLevelUseCase.progress(participant.getMember().getId(), 3);
        // }
    }

    private void validateActivityCategory(Activity activity) {
        if (ActivityCategory.DEFAULT.equals(activity.getCategory())) {
            throw new InvalidStateException(ErrorCode.DEFAULT_ACTIVITY_STATUS_IMMUTABLE_EXCEPTION);
        }
    }
}
