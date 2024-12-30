package com.stumeet.server.studymember.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.event.EventPublisher;
import com.stumeet.server.studymember.adapter.out.event.model.GrapePraiseAlertEvent;
import com.stumeet.server.studymember.application.port.in.GrapePraiseSendUseCase;
import com.stumeet.server.studymember.application.port.in.command.GrapeSendCommand;
import com.stumeet.server.review.application.port.out.GrapeSavePort;
import com.stumeet.server.studymember.application.port.out.StudyMemberQueryPort;
import com.stumeet.server.studymember.domain.Grape;
import com.stumeet.server.studymember.domain.StudyMember;
import com.stumeet.server.study.application.port.in.StudyQueryUseCase;
import com.stumeet.server.studymember.application.port.in.HandleGrapeStatusUseCase;
import com.stumeet.server.studymember.application.port.in.StudyMemberValidationUseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@Transactional
@RequiredArgsConstructor
public class GrapePraiseSendService implements GrapePraiseSendUseCase {
    private final StudyQueryUseCase studyQueryUseCase;
    private final HandleGrapeStatusUseCase handleGrapeStatusUseCase;

    private final GrapeSavePort grapeSavePort;
    private final StudyMemberQueryPort studyMemberQueryPort;

    @Override
    public void sendGrape(GrapeSendCommand command) {
        StudyMember receiver = studyMemberQueryPort.findStudyMember(command.studyMemberId());
        StudyMember sender = studyMemberQueryPort.findStudyMember(receiver.getStudy().getId(),
            command.senderMemberId());

        validateReceiverAndSender(receiver, sender);

        Grape grape = createGrape(receiver, command);
        grapeSavePort.save(grape);

        handleGrapeStatusUseCase.markGrapeSent(sender.getId());

        EventPublisher.raise(
            new GrapePraiseAlertEvent(this, receiver.getStudy().getId(), receiver.getMember().getId()));
    }

    private void validateReceiverAndSender(StudyMember receiver, StudyMember sender) {
        sender.checkAlreadySentGrape();
        sender.checkSelfPraise(receiver.getMember().getId());
    }

    private Grape createGrape(StudyMember receiver, GrapeSendCommand command) {
        return Grape.create(
            receiver.getMember().getId(),
            receiver.getStudy().getId(),
            studyQueryUseCase.getStudyName(receiver.getStudy().getId()),
            command.complimentType()
        );
    }
}
