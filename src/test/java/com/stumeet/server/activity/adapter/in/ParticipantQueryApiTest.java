package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.ActivityStub;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ParticipantQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("활동 참여자 리스트 조회")
    class FindAllByActivityId {

        private static final String PATH = "/api/v1/studies/{studyId}/activities/{activityId}/members";

        @Test
        @WithMockMember
        @DisplayName("[성공] 활동 참여자 리스트를 조회한다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("get-activity-participants/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.participants[].id").description("참여자 ID"),
                                    fieldWithPath("data.participants[].name").description("참여자 이름"),
                                    fieldWithPath("data.participants[].profileImageUrl").description("참여자 프로필 이미지 URL"),
                                    fieldWithPath("data.participants[].status").description("참여자 상태")
                            )));

        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 예외가 발생합니다.")
        void notFoundStudyTest() throws Exception {
            Long invalidStudyId = StudyStub.getInvalidStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, invalidStudyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(ErrorCode.STUDY_NOT_FOUND.getMessage()))
                    .andDo(document("get-activity-participants/fail/not-found-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 활동이 존재하지 않는 경우 예외가 발생합니다.")
        void notFoundActivityTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long invalidActivityId = ActivityStub.getInvalidActivityId();

            mockMvc.perform(get(PATH, studyId, invalidActivityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(ErrorCode.ACTIVITY_NOT_FOUND.getMessage()))
                    .andDo(document("get-activity-participants/fail/not-found-activity",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID"),
                                    parameterWithName("activityId").description("활동 ID")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}