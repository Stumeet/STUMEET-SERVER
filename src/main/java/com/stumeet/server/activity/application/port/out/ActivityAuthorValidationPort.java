package com.stumeet.server.activity.application.port.out;

public interface ActivityAuthorValidationPort {

    boolean isNotActivityAuthor(Long memberId, Long activityId);
}
