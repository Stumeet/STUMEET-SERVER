package com.stumeet.server.studymember.adapter.in.web;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class AdminDelegationApiTest extends ApiTest {

    @Nested
    @DisplayName("관리자 위임")
    class DelegateAdmin {
        private static final String PATH = "/api/v1/studies/{studyId}/members/{memberId}/admin/delegate";

        @DisplayName("[성공] 관리자 위임에 성공한다.")
        @Test
        @WithMockMember
        void success() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = 2L;

            mockMvc.perform(patch(PATH, studyId, memberId)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("delegate-admin/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("관리자 위임할 멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @DisplayName("[실패] 요청자가 해당 스터디의 관리자가 아닌 경우 관리자 위임에 실패한다.")
        @Test
        @WithMockMember(id = 2L)
        void fail_when_not_admin() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(patch(PATH, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("delegate-admin/fail/not-admin",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("관리자 위임할 멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @DisplayName("[실패] 위임 대상자가 스터디 멤버가 아닌 경우 관리자 위임에 실패한다.")
        @Test
        @WithMockMember
        void fail_when_not_study_member() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = 3L;

            mockMvc.perform(patch(PATH, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("delegate-admin/fail/member-not-joined",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("관리자 위임할 멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}