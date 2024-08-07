package com.stumeet.server.activity.adapter.in;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.stumeet.server.activity.adapter.in.request.ParticipantStatusUpdateRequest;
import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.ActivityParticipantStub;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class ParticipantStatusUpdateApiTest extends ApiTest {

    @Nested
    @DisplayName("활동 수정 API")
    class UpdateActivity {

        private final String path = "/api/v1/studies/{studyId}/members/{memberId}/activities/{activityId}/status";

        @Test
        @WithMockMember
        @DisplayName("[성공] 멤버의 활동 상태 수정에 성공한다.")
        void success() throws Exception {
            ParticipantStatusUpdateRequest request = ActivityParticipantStub.getParticipantStatusUpdateRequest();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), 2L, ActivityStub.getMeetActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isOk())
                    .andDo(document("update-activity-participant-status/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("멤버 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            requestFields(
                                    fieldWithPath("participantId").description("참가자 ID"),
                                    fieldWithPath("status").description("활동 멤버 상태")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}