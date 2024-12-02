package com.stumeet.server.review.application.port.in.command;

import java.util.List;

public record ReviewRegisterCommand(
    Long reviewerId,
    Long revieweeId,
    Long studyId,
    Integer rate,
    String content,
    List<String> reviewTags
) {
}
