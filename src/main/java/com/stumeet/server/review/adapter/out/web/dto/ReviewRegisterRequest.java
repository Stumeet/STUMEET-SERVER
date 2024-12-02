package com.stumeet.server.review.adapter.out.web.dto;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ReviewRegisterRequest(
    @NotNull(message = "리뷰 대상 ID는 null일 수 없습니다.")
    Long revieweeId,

    @NotNull(message = "스터디 ID는 null일 수 없습니다.")
    Long studyId,

    @NotNull(message = "평점은 null일 수 없습니다.")
    @Range(min = 1, max = 5, message = "평점 범위를 벗어났습니다. 1에서 5 사이의 값을 입력해주세요.")
    Integer rate,

    String content,

    @NotEmpty(message = "리뷰 태그 목록은 비어있을 수 없습니다.")
    List<String> reviewTags
) {
}
