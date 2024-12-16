package com.stumeet.server.review.adapter.out.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.review.adapter.out.web.dto.ReviewDetailResponse;
import com.stumeet.server.review.adapter.out.web.dto.ReviewTagStatsResponse;
import com.stumeet.server.review.application.port.in.ReviewQueryUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewQueryApi {
    private final ReviewQueryUseCase reviewQueryUseCase;

    @GetMapping("/reviews")
    public ResponseEntity<ApiResponse<List<ReviewDetailResponse>>> getReviews(
        @AuthenticationPrincipal LoginMember member,
        @RequestParam Integer size,
        @RequestParam Integer page,
        @RequestParam String sort
    ) {
        List<ReviewDetailResponse> response =
            reviewQueryUseCase.getMemberReview(member.getId(), size, page, sort);

        return new ResponseEntity<>(
            ApiResponse.success(SuccessCode.GET_SUCCESS, response),
            HttpStatus.OK);
    }

    @GetMapping("/reviews/tags/stats")
    public ResponseEntity<ApiResponse<ReviewTagStatsResponse>> getReviewStats(
        @AuthenticationPrincipal LoginMember member
    ) {
        ReviewTagStatsResponse response =
            reviewQueryUseCase.getMemberReviewTagStats(member.getId());

        return new ResponseEntity<>(
            ApiResponse.success(SuccessCode.GET_SUCCESS, response),
            HttpStatus.OK);
    }
}
