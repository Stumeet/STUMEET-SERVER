package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class AlreadyStudyJoinMemberException extends InvalidStateException {
    private static final String MESSAGE = "이미 스터디에 가입한 멤버입니다. studyId={0}, memberId={1}";
    public AlreadyStudyJoinMemberException(Long studyId, Long memberId) {
        super(MessageFormat.format(MESSAGE, studyId, memberId), ErrorCode.ALREADY_STUDY_JOIN_MEMBER_EXCEPTION);
    }
}
