package com.stumeet.server.review.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.member.application.port.in.MemberLevelUseCase;
import com.stumeet.server.review.application.port.in.ReviewRegisterUseCase;
import com.stumeet.server.review.application.port.in.ReviewValidationUseCase;
import com.stumeet.server.review.application.port.in.command.ReviewRegisterCommand;
import com.stumeet.server.review.application.port.out.ReviewSavePort;
import com.stumeet.server.review.domain.Review;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReviewRegisterService implements ReviewRegisterUseCase {

    private final ReviewValidationUseCase reviewValidationUseCase;
    private final StudyValidationUseCase studyValidationUseCase;
    private final StudyMemberValidationUseCase studyMemberValidationUseCase;
    private final MemberLevelUseCase memberLevelUseCase;

    private final ReviewSavePort reviewSavePort;

    @Override
    public void register(ReviewRegisterCommand command) {
        studyValidationUseCase.checkById(command.studyId());
        studyValidationUseCase.checkLegacyStudy(command.studyId());
        studyMemberValidationUseCase.checkStudyJoinMember(command.studyId(), command.reviewerId());
        studyMemberValidationUseCase.checkStudyJoinMember(command.studyId(), command.revieweeId());
        reviewValidationUseCase.validateDuplicate(command.studyId(), command.reviewerId(), command.revieweeId());

        Review review = Review.create(
            command.reviewerId(),
            command.revieweeId(),
            command.studyId(),
            command.rate(),
            command.content(),
            command.reviewTags()
        );

        reviewSavePort.save(review);

        // 경험치 처리
        memberLevelUseCase.progress(command.reviewerId(), 40);
    }
}
