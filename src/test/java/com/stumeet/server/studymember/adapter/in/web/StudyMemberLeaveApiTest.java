package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudyMemberLeaveApiTest extends ApiTest {

    @Nested
    @DisplayName("스터디 멤버 탈퇴 API")
    class Leave {

        private final String path = "/api/v1/studies/{studyId}/members/me";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 멤버 탈퇴에 성공한다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(delete(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("leave-study/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 스터디 멤버 탈퇴에 실패한다.")
        void notExistStudyTest() throws Exception {
            Long invalidStudyId = StudyStub.getInvalidStudyId();

            mockMvc.perform(delete(path, invalidStudyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("leave-study/fail/not-exist-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 0L)
        @DisplayName("[실패] 스터디에 가입된 멤버가 아닌 경우 스터디 멤버 탈퇴에 실패한다.")
        void notStudyJoinMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(delete(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("leave-study/fail/not-joined-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }

    @Nested
    @DisplayName("스터디 멤버 강퇴 API")
    class Kick {

        private final String path = "/api/v1/studies/{studyId}/members/{memberId}";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 멤버 강퇴에 성공한다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(delete(path, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("kick-study/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 스터디 멤버 강퇴에 실패한다.")
        void notExistStudyTest() throws Exception {
            Long invalidStudyId = StudyStub.getInvalidStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(delete(path, invalidStudyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("kick-study/fail/not-exist-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디에 가입된 멤버가 아닌 경우 스터디 멤버 강퇴에 실패한다.")
        void notJoinedStudyMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long invalidMemberId = MemberStub.getInvalidMemberId();

            mockMvc.perform(delete(path, studyId, invalidMemberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("kick-study/fail/not-joined-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 0L)
        @DisplayName("[실패] 스터디 관리자가 아닌 경우 스터디 멤버 강퇴에 실패한다.")
        void notAdminTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long memberId = MemberStub.getMemberId();

            mockMvc.perform(delete(path, studyId, memberId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("kick-study/fail/not-admin",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("memberId").description("멤버 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}