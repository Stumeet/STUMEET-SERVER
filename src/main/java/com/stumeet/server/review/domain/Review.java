package com.stumeet.server.review.domain;

import java.util.List;

import org.hibernate.validator.constraints.Range;

import com.stumeet.server.review.domain.exception.InvalidReviewTagException;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Review {
    private Long id;

    private Long reviewerId;

    private Long revieweeId;

    private Long studyId;

    @Range(min = 1, max = 5)
    private Integer rate;

    private String content;

    private List<ReviewTag> reviewTags;

    @Builder
    private Review(Long id, Long reviewerId, Long revieweeId, Long studyId, Integer rate, String content,
        List<ReviewTag> reviewTags) {
        validateReviewTagCount(reviewTags);

        this.id = id;
        this.reviewerId = reviewerId;
        this.revieweeId = revieweeId;
        this.studyId = studyId;
        this.rate = rate;
        this.content = content;
        this.reviewTags = reviewTags;
    }

    private void validateReviewTagCount(List<ReviewTag> reviewTags) {
        if (reviewTags.isEmpty() || reviewTags.size() > 3) {
            throw new InvalidReviewTagException();
        }
    }

    public static Review create(Long reviewerId, Long revieweeId, Long studyId, Integer rate, String content, List<String> reviewTagNames) {
        return Review.builder()
            .reviewerId(reviewerId)
            .revieweeId(revieweeId)
            .studyId(studyId)
            .rate(rate)
            .content(content)
            .reviewTags(reviewTagNames.stream()
                .map(ReviewTag::valueOf)
                .distinct()
                .toList())
            .build();
    }
}