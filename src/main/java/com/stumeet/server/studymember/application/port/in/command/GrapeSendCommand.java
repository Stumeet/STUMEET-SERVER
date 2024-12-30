package com.stumeet.server.studymember.application.port.in.command;

import com.stumeet.server.studymember.domain.ComplimentType;

import lombok.Builder;

public record GrapeSendCommand(
    Long senderMemberId,
    Long studyMemberId,
    ComplimentType complimentType
) {
    @Builder
    public GrapeSendCommand(Long senderMemberId, Long studyMemberId, String complimentTypeName) {
        this(senderMemberId, studyMemberId, ComplimentType.safeValueOf(complimentTypeName));
    }
}
