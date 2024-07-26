package com.stumeet.server.activity.application.service;

import com.stumeet.server.activity.application.port.in.ActivityAuthorityValidationUseCase;
import com.stumeet.server.activity.application.port.out.ActivityAuthorValidationPort;
import com.stumeet.server.activity.domain.exception.ActivityManagementAccessDeniedException;
import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.studymember.application.port.out.StudyMemberValidationPort;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ActivityAuthorityValidationService implements ActivityAuthorityValidationUseCase {

    private final StudyMemberValidationPort studyMemberValidationPort;
    private final ActivityAuthorValidationPort activityAuthorValidationPort;

    @Override
    public void checkDeleteAuthority(Long studyId, Long memberId, Long activityId) {
        if (!studyMemberValidationPort.isAdmin(studyId, memberId) && activityAuthorValidationPort.isNotActivityAuthor(memberId, activityId)) {
            throw new ActivityManagementAccessDeniedException();
        }
    }
}
