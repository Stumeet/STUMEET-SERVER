package com.stumeet.server.studymember.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudyMemberQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("스터디 멤버 전체 조회")
    class GetStudyMembers {

        private final String path = "/api/v1/studies/{studyId}/members";

        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 멤버를 조회할 수 있다.")
        void SuccessTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-study-members/success",
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
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.studyMembers[]").description("스터디 멤버 목록"),
                                    fieldWithPath("data.studyMembers[].id").description("스터디 멤버 ID"),
                                    fieldWithPath("data.studyMembers[].name").description("스터디 멤버 이름"),
                                    fieldWithPath("data.studyMembers[].image").description("스터디 멤버 프로필 이미지"),
                                    fieldWithPath("data.studyMembers[].region").description("스터디 멤버 지역"),
                                    fieldWithPath("data.studyMembers[].profession").description("스터디 멤버 분야"),
                                    fieldWithPath("data.studyMembers[].isAdmin").description("스터디 관리자 여부")
                            )));

        }

        @Test
        @WithMockMember(id = 0)
        @DisplayName("[실패] 해당 스터디에 가입한 멤버가 요청한 것이 아니라면 예외가 발생한다.")
        void NotJoinedMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();

            mockMvc.perform(get(path, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isForbidden())
                    .andDo(document("get-study-members/fail/not-joined-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
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
        @DisplayName("[실패] 스터디가 존재하지 않으면 예외가 발생한다.")
        void NotExistStudyTest() throws Exception {
            Long notExistStudyId = StudyStub.getInvalidStudyId();

            mockMvc.perform(get(path, notExistStudyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken()))
                    .andExpect(status().isNotFound())
                    .andDo(document("get-study-members/fail/not-exist-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }

}