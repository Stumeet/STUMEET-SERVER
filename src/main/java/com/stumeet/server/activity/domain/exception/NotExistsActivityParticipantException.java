package com.stumeet.server.activity.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsActivityParticipantException extends NotExistsException {
    private static final String MESSAGE = "존재하지 않는 활동 참여자입니다. 입력받은 참여자 ID : {0}";

    public NotExistsActivityParticipantException(Long participantId) {
        super(MessageFormat.format(MESSAGE, participantId), ErrorCode.ACTIVITY_PARTICIPANT_NOT_FOUND);
    }
}
