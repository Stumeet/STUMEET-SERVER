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

        @Test
        @WithMockMember
        @DisplayName("[실패] 참여자 상태가 존재하지 않는 경우 멤버의 활동 상태 수정에 실패한다.")
        void fail_when_activity_status_not_found() throws Exception {
            ParticipantStatusUpdateRequest request = ParticipantStatusUpdateRequest.builder()
                    .participantId(7L)
                    .status("HELLO")
                    .build();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), 2L, ActivityStub.getMeetActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isNotFound())
                    .andDo(document("update-activity-participant-status/fail/activity-status-not-found",
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

        @Test
        @WithMockMember
        @DisplayName("[실패] 요청한 스터디가 존재하지 않는 경우 멤버의 활동 상태 수정에 실패한다.")
        void fail_when_study_not_found() throws Exception {
            ParticipantStatusUpdateRequest request = ActivityParticipantStub.getParticipantStatusUpdateRequest();

            mockMvc.perform(patch(path, StudyStub.getInvalidStudyId(), 2L, ActivityStub.getMeetActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isNotFound())
                    .andDo(document("update-activity-participant-status/fail/study-not-found",
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

        @Test
        @WithMockMember(id = 2L)
        @DisplayName("[실패] 요청한 멤버가 관리자가 아닌 경우 멤버의 활동 상태 수정에 실패한다.")
        void fail_when_member_is_not_admin() throws Exception {
            ParticipantStatusUpdateRequest request = ActivityParticipantStub.getParticipantStatusUpdateRequest();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), 2L, ActivityStub.getMeetActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isForbidden())
                    .andDo(document("update-activity-participant-status/fail/member-is-not-admin",
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

        @Test
        @WithMockMember
        @DisplayName("[실패] 상태 변경되는 멤버가 스터디의 멤버가 아닌 경우 멤버의 활동 상태 수정에 실패한다.")
        void fail_when_member_is_not_joined_member() throws Exception {
            ParticipantStatusUpdateRequest request = ActivityParticipantStub.getParticipantStatusUpdateRequest();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), 3L, ActivityStub.getMeetActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isForbidden())
                    .andDo(document("update-activity-participant-status/fail/member-is-not-joined-member",
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

        @Test
        @WithMockMember
        @DisplayName("[실패] 상태 수정 요청한 활동의 카테고리가 자유인 경우 멤버의 활동 상태 수정에 실패한다.")
        void fail_when_activity_category_default() throws Exception {
            ParticipantStatusUpdateRequest request = ActivityParticipantStub.getParticipantStatusUpdateRequest();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), 2L, ActivityStub.getDefaultActivity())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("update-activity-participant-status/fail/activity-is-default",
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