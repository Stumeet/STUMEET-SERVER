package com.stumeet.server.study.application.service;

import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.notification.application.port.in.InitializeTopicUseCase;
import com.stumeet.server.notification.application.port.in.SubscribeTopicUseCase;
import com.stumeet.server.study.application.port.in.StudyImageUpdateUseCase;
import com.stumeet.server.study.application.port.out.StudyTagCommandPort;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyCreateUseCase;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;
import com.stumeet.server.study.application.port.in.mapper.StudyUseCaseMapper;
import com.stumeet.server.study.application.port.out.StudyCommandPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;

import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class StudyCreateService implements StudyCreateUseCase {

    private final StudyMemberJoinUseCase memberJoinUseCase;
    private final MemberValidationUseCase memberValidationUseCase;
    private final StudyImageUpdateUseCase studyImageUpdateUseCase;
    private final InitializeTopicUseCase initializeTopicUseCase;
    private final SubscribeTopicUseCase subscribeTopicUseCase;

    private final StudyCommandPort studyCommandPort;
    private final StudyTagCommandPort studyTagCommandPort;

    private final StudyUseCaseMapper studyUseCaseMapper;

    @Override
    public Long create(Long memberId, StudyCreateCommand command, MultipartFile mainImageFile) {
        memberValidationUseCase.checkById(memberId);

        Study study = Study.create(command);
        long studyCreatedId = studyCommandPort.save(study).getId();
        studyImageUpdateUseCase.updateMainImage(studyCreatedId, mainImageFile);

        studyTagCommandPort.saveAllStudyTags(study.getStudyTags(), studyCreatedId);
        memberJoinUseCase.join(studyUseCaseMapper.toAdminStudyMemberJoinCommand(memberId, studyCreatedId));

        initializeTopicUseCase.initializeStudyNoticeTopic(study.getId());
        subscribeTopicUseCase.subscribeStudyNoticeTopic(memberId, study.getId());

        return studyCreatedId;
    }
}
