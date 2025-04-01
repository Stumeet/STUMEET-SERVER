package com.stumeet.server.review.adapter.out.web;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.review.adapter.out.web.dto.ReviewRegisterRequest;
import com.stumeet.server.stub.ReviewStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class ReviewRegisterApiTest extends ApiTest {

    @Nested
    @DisplayName("멤버 리뷰 등록 API")
    class RegisterReview {

        private final String path = "/api/v1/reviews";

        @Test
        @WithMockMember
        @DisplayName("[성공] 리뷰 등록에 성공한다.")
        void success() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getReviewRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isCreated())
                .andDo(document("register-review/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 스터디가 존재하지 않는 경우 리뷰 등록에 실패한다.")
        void fail_when_study_not_exists() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getNotExistStudyReviewRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isNotFound())
                .andDo(document("register-review/fail/study-not-found",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember(id = 3L)
        @DisplayName("[실패] 리뷰어가 스터디 멤버가 아닌 경우 리뷰 등록에 실패한다.")
        void fail_when_reviewer_not_study_joined_member() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getReviewRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isForbidden())
                .andDo(document("register-review/fail/reviewer-not-joined",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 리뷰 대상 멤버가 스터디 멤버가 아닌 경우 리뷰 등록에 실패한다.")
        void fail_when_reviewee_not_study_joined_member() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getRevieweeNotJoinedReviewRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isForbidden())
                .andDo(document("register-review/fail/reviewee-not-joined",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 평점이 범위에서 벗어난 경우 리뷰 등록에 실패한다.")
        void fail_when_rate_out_of_range() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getRateOutOfRangeReviewRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(document("register-review/fail/rate-out-of-range",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("요청 값에 대한 검증 메시지"),
                        fieldWithPath("data[]").description("응답 데이터"),
                        fieldWithPath("data[].message").description("상세 응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 리뷰 태그의 개수가 유효하지 않은 경우 리뷰 등록에 실패한다.")
        void fail_when_review_tag_count_invalid() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getInvalidReviewTagCountReviewRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(document("register-review/fail/invalid-review-tag-count",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("요청 값에 대한 검증 메시지"),
                        fieldWithPath("data[]").description("응답 데이터"),
                        fieldWithPath("data[].message").description("상세 응답 메시지")
                    )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 이미 작성된 리뷰가 존재하는 경우 경우 리뷰 등록에 실패한다.")
        void fail_when_review_already_exists() throws Exception {
            ReviewRegisterRequest request = ReviewStub.getAlreadyReviewedRegisterRequest();

            mockMvc.perform(post(path)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(toJson(request)))
                .andExpect(status().isConflict())
                .andDo(document("register-review/fail/review-already-exists",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    requestFields(
                        fieldWithPath("revieweeId")
                            .description("리뷰 받을 멤버의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("studyId")
                            .description("리뷰가 속한 스터디의 ID")
                            .attributes(key("constraints").value("NotNull")),
                        fieldWithPath("rate")
                            .description("리뷰 평점")
                            .attributes(key("constraints").value("NotNull, Range: 1 ~ 5")),
                        fieldWithPath("content")
                            .description("리뷰 내용")
                            .optional(),
                        fieldWithPath("reviewTags")
                            .description("리뷰 태그 목록")
                            .attributes(key("constraints").value("NotEmpty, 최소 1개, 최대 3개"))
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지")
                    )));
        }
    }
}