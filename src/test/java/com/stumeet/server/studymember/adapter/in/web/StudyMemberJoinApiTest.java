package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.response.ErrorCode;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudyMemberJoinApiTest extends ApiTest {

    @Nested
    @DisplayName("스터디 가입 API")
    class Join {

        private static final String PATH = "/api/v1/studies/{studyId}/members";

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[성공] 스터디 가입에 성공한다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("study-member-join/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(id = 0L)
        @DisplayName("[실패] 존재하지 않는 멤버인 경우 예외가 발생한다.")
        void failByNotExistMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(ErrorCode.MEMBER_NOT_FOUND.getHttpStatusCode()))
                    .andExpect(jsonPath("$.message").value(ErrorCode.MEMBER_NOT_FOUND.getMessage()))
                    .andDo(document("study-member-join/fail/not-exist-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 존재하지 않는 스터디인 경우 예외가 발생한다.")
        void failByNotExistStudyTest() throws Exception {
            Long studyId = StudyStub.getInvalidStudyId();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.code").value(ErrorCode.STUDY_NOT_FOUND.getHttpStatusCode()))
                    .andExpect(jsonPath("$.message").value(ErrorCode.STUDY_NOT_FOUND.getMessage()))
                    .andDo(document("study-member-join/fail/not-exist-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디에 이미 가입한 멤버인 경우 예외가 발생한다.")
        void failByAlreadyJoinMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.code").value(ErrorCode.ALREADY_STUDY_JOIN_MEMBER_EXCEPTION.getHttpStatusCode()))
                    .andExpect(jsonPath("$.message").value(ErrorCode.ALREADY_STUDY_JOIN_MEMBER_EXCEPTION.getMessage()))
                    .andDo(document("study-member-join/fail/already-join-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

    }
}