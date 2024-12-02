package com.stumeet.server.review.adapter.out.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.review.adapter.out.web.dto.ReviewRegisterRequest;
import com.stumeet.server.review.application.port.in.ReviewRegisterUseCase;
import com.stumeet.server.review.application.port.in.command.ReviewRegisterCommand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewRegisterApi {

    private final ReviewRegisterUseCase reviewRegisterUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> register(
        @AuthenticationPrincipal LoginMember member,
        @RequestBody @Valid ReviewRegisterRequest request
    ) {
        ReviewRegisterCommand command = new ReviewRegisterCommand(
            member.getId(),
            request.revieweeId(),
            request.studyId(),
            request.rate(),
            request.content(),
            request.reviewTags()
        );

        reviewRegisterUseCase.register(command);

        return new ResponseEntity<>(
            ApiResponse.success(SuccessCode.REVIEW_CREATE_SUCCESS),
            HttpStatus.CREATED);
    }
}
