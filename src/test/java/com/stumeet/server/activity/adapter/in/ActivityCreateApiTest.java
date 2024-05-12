package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ActivityCreateApiTest extends ApiTest {

    @Nested
    @DisplayName("활동 생성")
    class Create {

        private static final String PATH = "/api/v1/studies/{studyId}/activities";

        @Test
        @WithMockMember
        @DisplayName("[성공] 활동 생성에 성공한다.")
        void successTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isCreated())
                    .andDo(document("create-activity/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("category").description("활동 카테고리"),
                                    fieldWithPath("title").description("활동 제목"),
                                    fieldWithPath("content").description("활동 내용"),
                                    fieldWithPath("images[]").description("활동 이미지 URL 리스트"),
                                    fieldWithPath("isNotice").description("공지 여부"),
                                    fieldWithPath("startDate").description("활동 시작 일시"),
                                    fieldWithPath("endDate").description("활동 종료 일시"),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("participants").description("참여자 ID 리스트")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 활동 생성 요청 시 필수값이 누락된 경우 예외가 발생한다.")
        void invalidRequestTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            String request = ActivityStub.getInvalidCreateActivityJson();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(request))
                    .andExpect(status().isBadRequest())
                    .andDo(document("create-activity/fail/invalid-request",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("category").description("활동 카테고리"),
                                    fieldWithPath("title").description("활동 제목"),
                                    fieldWithPath("content").description("활동 내용"),
                                    fieldWithPath("images[]").description("활동 이미지 URL 리스트"),
                                    fieldWithPath("isNotice").description("공지 여부"),
                                    fieldWithPath("startDate").description("활동 시작 일시"),
                                    fieldWithPath("endDate").description("활동 종료 일시"),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("participants").description("참여자 ID 리스트")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data[].message").description("유효성 검사 실패 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 존재하지 않는 스터디로 활동 생성 요청 시 예외가 발생한다.")
        void notExistsStudyTest() throws Exception {
            Long studyId = StudyStub.getInvalidStudyId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isNotFound())
                    .andDo(document("create-activity/fail/not-exists-study",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("category").description("활동 카테고리"),
                                    fieldWithPath("title").description("활동 제목"),
                                    fieldWithPath("content").description("활동 내용"),
                                    fieldWithPath("images[]").description("활동 이미지 URL 리스트"),
                                    fieldWithPath("isNotice").description("공지 여부"),
                                    fieldWithPath("startDate").description("활동 시작 일시"),
                                    fieldWithPath("endDate").description("활동 종료 일시"),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("participants").description("참여자 ID 리스트")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(id = 2L)
        @DisplayName("[실패] 생성 요청을 한 사용자가 스터디의 관리자가 아닌 경우 예외가 발생한다.")
        void notAdminTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isForbidden())
                    .andDo(document("create-activity/fail/not-admin",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            pathParameters(
                                    parameterWithName("studyId").description("스터디 ID")
                            ),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("category").description("활동 카테고리"),
                                    fieldWithPath("title").description("활동 제목"),
                                    fieldWithPath("content").description("활동 내용"),
                                    fieldWithPath("images[]").description("활동 이미지 URL 리스트"),
                                    fieldWithPath("isNotice").description("공지 여부"),
                                    fieldWithPath("startDate").description("활동 시작 일시"),
                                    fieldWithPath("endDate").description("활동 종료 일시"),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("participants").description("참여자 ID 리스트")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }
    }
}