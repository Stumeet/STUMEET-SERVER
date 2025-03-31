package com.stumeet.server.review.adapter.out.web;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.review.domain.ReviewSort;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class ReviewQueryApiTest extends ApiTest {
    @Nested
    @DisplayName("멤버 리뷰 목록 조회 API")
    class GetByMemberId {

        private static final String PATH = "/api/v1/reviews";

        @Test
        @WithMockMember(id = 4L)
        @DisplayName("[성공] 멤버의 리뷰 목록 조회에 성공한다.")
        void success() throws Exception {
            int size = 2;
            int page = 0;
            ReviewSort sort = ReviewSort.LATEST;

            mockMvc.perform(get(PATH)
                    .param("size", Integer.toString(size))
                    .param("page", Integer.toString(page))
                    .param("sort", sort.name())
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SuccessCode.GET_SUCCESS.getMessage()))
                .andDo(document("get-member-reviews/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    queryParameters(
                        parameterWithName("size").description("페이지 크기"),
                        parameterWithName("page").description("페이지 번호"),
                        parameterWithName("sort").description("정렬 기준")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("message").description("응답 메시지"),
                        fieldWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.items").description("리뷰 목록"),
                        fieldWithPath("data.items[].id").description("리뷰 ID"),
                        fieldWithPath("data.items[].rate").description("별점"),
                        fieldWithPath("data.items[].content").description("내용"),
                        fieldWithPath("data.items[].createdAt").description("생성일자"),
                        fieldWithPath("data.items[].tags[]").description("리뷰 태그 목록"),
                        fieldWithPath("data.pageInfo").description("페이지 메타 정보"),
                        fieldWithPath("data.pageInfo.totalPages").description("전체 페이지 수"),
                        fieldWithPath("data.pageInfo.totalElements").description("전체 요소 수"),
                        fieldWithPath("data.pageInfo.currentPage").description("현재 페이지"),
                        fieldWithPath("data.pageInfo.pageSize").description("페이지 크기")
                    )
                ));
        }
    }

    @Nested
    @DisplayName("멤버 리뷰 태그 통계 조회 API")
    class GetMemberReviewTagStats {
        private static final String PATH = "/api/v1/reviews/tags/stats";

        @Test
        @WithMockMember(id = 4L)
        @DisplayName("[성공] 멤버의 리뷰 목록 조회에 성공한다.")
        void success() throws Exception {
            mockMvc.perform(get(PATH)
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SuccessCode.GET_SUCCESS.getMessage()))
                .andDo(document("get-member-review-tags-stats/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("message").description("응답 메시지"),
                        fieldWithPath("data").description("리뷰 태그 개수 통계"),
                        fieldWithPath("data.totalCount").description("총 리뷰 태그 개수"),
                        fieldWithPath("data.tagCountStats[].reviewTagName").description("리뷰 태그 이름"),
                        fieldWithPath("data.tagCountStats[].count").description("리뷰 태그 개수")
                    )
                ));
        }
    }
}