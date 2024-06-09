package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.common.response.SuccessCode;
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
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ActivityQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("활동 단일 조회")
    class GetById {

        private static final String PATH = "/api/v1/studies/{studyId}/activities/{activityId}";


        @Test
        @WithMockMember
        @DisplayName("[성공] 스터디 활동 단일 조회에 성공합니다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.message").value(SuccessCode.GET_SUCCESS.getMessage()))
                    .andDo(document("get-activity-by-id/success",
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
                                    fieldWithPath("data.id").description("활동 ID"),
                                    fieldWithPath("data.title").description("활동 제목"),
                                    fieldWithPath("data.content").description("활동 내용"),
                                    fieldWithPath("data.imageUrl[].id").description("활동 이미지의 아이디"),
                                    fieldWithPath("data.imageUrl[].imageUrl").description("활동 이미지의 URL"),
                                    fieldWithPath("data.author.memberId").description("활동 작성자 ID"),
                                    fieldWithPath("data.author.name").description("활동 작성자 이름"),
                                    fieldWithPath("data.author.profileImageUrl").description("활동 작성자 프로필 이미지 URL"),
                                    fieldWithPath("data.participants[].memberId").description("참여자 ID"),
                                    fieldWithPath("data.participants[].name").description("참여자 이름"),
                                    fieldWithPath("data.participants[].profileImageUrl").description("참여자 프로필 이미지 URL"),
                                    fieldWithPath("data.status").description("나의 활동 상태"),
                                    fieldWithPath("data.startDate").description("활동 시작일"),
                                    fieldWithPath("data.endDate").description("활동 종료일"),
                                    fieldWithPath("data.location").description("장소"),
                                    fieldWithPath("data.createdAt").description("활동 생성일")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 예외가 발생합니다.")
        void studyNotFoundTest() throws Exception {
            Long studyId = StudyStub.getInvalidStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(ErrorCode.STUDY_NOT_FOUND.getMessage()))
                    .andDo(document("get-activity-by-id/fail/study-not-found",
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
                            )
                    ));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 스터디에 가입하지 않은 사용자인 경우 예외가 발생합니다.")
        void notJoinedStudyTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isForbidden())
                    .andExpect(jsonPath("$.message").value(ErrorCode.STUDY_MEMBER_NOT_JOINED_EXCEPTION.getMessage()))
                    .andDo(document("get-activity-by-id/fail/not-joined-study",
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
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 활동이 존재하지 않는 경우 예외가 발생합니다.")
        void notFoundActivityTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            Long activityId = ActivityStub.getInvalidActivityId();

            mockMvc.perform(get(PATH, studyId, activityId)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.message").value(ErrorCode.ACTIVITY_NOT_FOUND.getMessage()))
                    .andDo(document("get-activity-by-id/fail/activity-not-found",
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
                            )
                    ));
        }
    }
}