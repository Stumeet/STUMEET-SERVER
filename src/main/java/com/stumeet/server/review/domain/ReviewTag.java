package com.stumeet.server.review.domain;

import lombok.Getter;

@Getter
public enum ReviewTag {
    DILIGENCE("귀차니즘"),
    TASK_COMMITMENT("과제 성실도"),
    CONSISTENT_ATTENDANCE("프로개근러"),
    BEST_LEADER("최고의 리더"),
    BEST_SUPPORTER("최고의 서포터"),
    FAST_RESPONSE("빠른 응답"),
    SLOW_RESPONSE("느린 응답"),
    DECISIVE_J("확신의 J"),
    FLEXIBLE_P("확신의 P"),
    MAX_RESPONSIBILITY("책임감 MAX"),
    MOOD_MAKER("분위기 메이커"),
    EFFORT_TOP("노력 1등"),
    NEEDS_IMPROVEMENT("분발이 필요해요"),
    NEAT_NOTES("깔끔한 필기"),
    PINPOINT_TEACHER("쪽집게 강사");

    private final String tagName;

    ReviewTag(String tagName) {
        this.tagName = tagName;
    }
}