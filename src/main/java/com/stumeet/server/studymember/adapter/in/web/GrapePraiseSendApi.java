package com.stumeet.server.studymember.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.adapter.in.web.dto.GrapeSendRequest;
import com.stumeet.server.studymember.application.port.in.GrapePraiseSendUseCase;
import com.stumeet.server.studymember.application.port.in.command.GrapeSendCommand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class GrapePraiseSendApi {
    private final GrapePraiseSendUseCase grapePraiseSendUseCase;

    @PostMapping("/grape")
    public ResponseEntity<ApiResponse<Void>> sendGrape(
        @AuthenticationPrincipal LoginMember member,
        @RequestBody @Valid GrapeSendRequest request
    ) {
        GrapeSendCommand command = GrapeSendCommand.builder()
            .senderMemberId(member.getId())
            .studyMemberId(request.studyMemberId())
            .complimentTypeName(request.complimentType())
            .build();
        grapePraiseSendUseCase.sendGrape(command);

        return new ResponseEntity<>(
            ApiResponse.success(SuccessCode.REVIEW_CREATE_SUCCESS),
            HttpStatus.CREATED);
    }
}
