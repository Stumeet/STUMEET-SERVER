package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.auth.model.LoginMember;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.studymember.adapter.in.web.mapper.StudyMemberJoinWebAdapterMapper;
import com.stumeet.server.studymember.application.port.in.StudyMemberJoinUseCase;
import com.stumeet.server.studymember.application.port.in.command.StudyMemberJoinCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudyMemberJoinApi {

    private final StudyMemberJoinUseCase studyMemberJoinUseCase;
    private final StudyMemberJoinWebAdapterMapper studyMemberJoinWebAdapterMapper;

    @PostMapping("/studies/{studyId}/members")
    public ResponseEntity<ApiResponse<Void>> join(
            @PathVariable Long studyId,
            @AuthenticationPrincipal LoginMember member
    ) {
        StudyMemberJoinCommand command = studyMemberJoinWebAdapterMapper.toCommand(studyId, member.getMember().getId());
        studyMemberJoinUseCase.join(command);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.STUDY_JOIN_SUCCESS));
    }
}
