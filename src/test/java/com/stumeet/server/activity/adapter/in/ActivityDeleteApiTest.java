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

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class ActivityDeleteApiTest extends ApiTest {

    @Nested
    @DisplayName("스터디 활동 삭제 API")
    class DeleteStudy {

        private final String PATH = "/api/v1/studies/{studyId}/activities/{activityId}";

        @Test
        @WithMockMember
        @DisplayName("[성공] 관리자가 스터디 활동 삭제를 성공한다.")
        void success_admin_delete_activity() throws Exception {
            mockMvc.perform(delete(PATH, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("delete-activity/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                    .description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 2L)
        @DisplayName("[성공] 작성자가 스터디 활동 삭제를 성공한다.")
        void success_author_delete_activity() throws Exception {
            mockMvc.perform(delete(PATH, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("delete-activity/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                    .description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 삭제를 시도하는 멤버가 관리자나 작성자가 아닌 일반 멤버인 경우 활동 삭제에 실패한다.")
        void fail_to_delete_when_member_not_joined_study() throws Exception {
            mockMvc.perform(delete(PATH, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("delete-activity/fail/forbidden",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                    .description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}