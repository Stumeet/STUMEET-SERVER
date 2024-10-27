package com.stumeet.server.notification.domain.exception;

import java.text.MessageFormat;

import com.stumeet.server.common.exception.model.NotExistsException;
import com.stumeet.server.common.response.ErrorCode;

public class NotExistsStudyNoticeTopicException extends NotExistsException {

    public static final String MESSAGE = "해당 스터디의 공지 토픽이 존재하지 않습니다. 입력받은 스터디 ID : {0}";

    public NotExistsStudyNoticeTopicException(Long studyId) {
        super(MessageFormat.format(MESSAGE, studyId), ErrorCode.STUDY_NOTICE_TOPIC_NOT_FOUND);
    }
}
