package com.stumeet.server.studymember.domain.exception;

import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;

import java.text.MessageFormat;

public class StudyMemberNotJoinedException extends InvalidStateException {
    private static final String MESSAGE = "스터디에 가입된 멤버가 아닙니다. 전달받은 studyId={0}, memberId={1}";
    public StudyMemberNotJoinedException(Long studyId, Long memberId) {
        super(MessageFormat.format(MESSAGE, studyId, memberId), ErrorCode.STUDY_MEMBER_NOT_JOINED_EXCEPTION);
    }
}
