package com.stumeet.server.activity.adapter.in;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.stumeet.server.activity.application.port.in.command.ActivityUpdateCommand;
import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.ActivityStub;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class ActivityUpdateApiTest extends ApiTest {

    @Nested
    @DisplayName("활동 수정 API")
    class UpdateActivity {

        private final String path = "/api/v1/studies/{studyId}/activities/{activityId}";

        @Test
        @WithMockMember(id = 4L)
        @DisplayName("[성공] 활동 수정에 성공한다.")
        void success() throws Exception {
            ActivityUpdateCommand command = ActivityStub.getActivityUpdateCommand();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(command)))
                    .andExpect(status().isOk())
                    .andDo(document("update-activity/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
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
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 작성자가 아닌 경우 수정에 실패한다.")
        void fail_when_member_is_not_author() throws Exception {
            ActivityUpdateCommand command = ActivityStub.getActivityUpdateCommand();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(command)))
                    .andExpect(status().isForbidden())
                    .andDo(document("update-activity/fail/not-author",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
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
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 스터디 멤버가 아닌 경우 활동 수정에 실패한다.")
        void fail_when_member_is_not_joined_member() throws Exception {
            ActivityUpdateCommand command = ActivityStub.getActivityUpdateCommand();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(command)))
                    .andExpect(status().isForbidden())
                    .andDo(document("update-activity/fail/not-joined-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
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
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 4L)
        @DisplayName("[실패] 모임/과제 유형으로 수정하는 경우 활동 기간이 null이면 활동 수정에 실패한다.")
        void fail_when_period_null() throws Exception {
            ActivityUpdateCommand command = ActivityStub.getNullPeriodActivityUpdateCommand();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(command)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("update-activity/fail/period-null",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
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
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember(id = 4L)
        @DisplayName("[실패] 모임/과제 유형으로 수정하는 경우 활동 기간이 유효하지 않으면 활동 수정에 실패한다.")
        void fail_when_period_invalid() throws Exception {
            ActivityUpdateCommand command = ActivityStub.getInvalidPeriodActivityUpdateCommand();

            mockMvc.perform(patch(path, StudyStub.getStudyId(), ActivityStub.getActivityId())
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(command)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("update-activity/fail/period-invalid",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                                            .description("서버로부터 전달받은 액세스 토큰")
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
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }
    }
}