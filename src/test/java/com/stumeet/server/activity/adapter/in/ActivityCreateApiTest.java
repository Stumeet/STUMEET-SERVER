package com.stumeet.server.activity.adapter.in;

import com.stumeet.server.activity.application.port.in.command.ActivityCreateCommand;
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
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
                                    fieldWithPath("participants").description("참여자 ID 리스트")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.id").description("활동 ID"),
                                    fieldWithPath("data.category").description("활동 유형"),
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
                                    fieldWithPath("data.link").description("링크"),
                                    fieldWithPath("data.createdAt").description("활동 생성일"),
                                    fieldWithPath("data.isAuthor").description("작성자 여부"),
                                    fieldWithPath("data.isAdmin").description("관리자 여부")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 활동 생성 요청 시 필수값이 누락된 경우 예외가 발생한다.")
        void invalidRequestTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getInvalidCreateActivity();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
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
        @DisplayName("[실패] 존재하지 않는 활동 카테고리로 생성 요청을 하는 경우 예외가 발생한다.")
        void notExistsActivityCategoryTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getInvalidCategoryCreateActivity();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(HttpStatus.BAD_REQUEST))
                    .andExpect(jsonPath("$.message").value(ErrorCode.INVALID_ACTIVITY_CATEGORY_EXCEPTION.getMessage()))
                    .andDo(document("create-activity/fail/not-exists-category",
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
                                    fieldWithPath("participants").description("참여자 ID 리스트")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 생성 요청을 한 사용자가 스터디 멤버가 아닌 경우 예외가 발생한다.")
        void notStudyMemberTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getDefaultActivityCreateCommand();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isForbidden())
                    .andDo(document("create-activity/fail/not-study-member",
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
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
        @DisplayName("[실패] 모임 활동 생성 시 장소 값이 NULL인 경우 예외가 발생한다.")
        void locationNullForMeetTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getMeetActivityCreateCommandLocationNull();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("create-activity/fail/location-null-for-meet",
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
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
        @DisplayName("[실패] 모임, 과제 활동 생성 시 활동 기간이 NULL인 경우 예외가 발생한다.")
        void activityPeriodRequiredTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getMeetActivityCreateCommandPeriodNull();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("create-activity/fail/period-null",
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
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
        @DisplayName("[실패] 모임, 과제 활동 생성 시 활동 기간이 유효하지 않은 경우 예외가 발생한다.")
        void activityPeriodInvalidTest() throws Exception {
            Long studyId = StudyStub.getStudyId();
            ActivityCreateCommand request = ActivityStub.getMeetActivityCreateCommandPeriodInvalid();

            mockMvc.perform(post(PATH, studyId)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("create-activity/fail/period-invalid",
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
                                    fieldWithPath("startDate").description("활동 시작 일시").optional(),
                                    fieldWithPath("endDate").description("활동 종료 일시").optional(),
                                    fieldWithPath("location").description("활동 장소").optional(),
                                    fieldWithPath("link").description("링크").optional(),
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