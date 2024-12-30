package com.stumeet.server.studymember.adapter.in.web;

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
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class GrapeQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("멤버 받은 포도알 목록 조회 API")
    class GetByMemberId {

        private static final String PATH = "/api/v1/reviews/grapes";

        @Test
        @WithMockMember
        @DisplayName("[성공] 멤버의 받은 포도알 목록 조회에 성공한다.")
        void success() throws Exception {
            Integer size = 10;
            Integer page = 0;

            mockMvc.perform(get(PATH)
                    .param("size", size.toString())
                    .param("page", page.toString())
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                .andExpect(status().isOk())
                .andDo(document("get-member-grapes/success",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    requestHeaders(
                        headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName())
                            .description("서버로부터 전달받은 액세스 토큰")
                    ),
                    queryParameters(
                        parameterWithName("size").description("페이지 크기"),
                        parameterWithName("page").description("페이지 번호")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("message").description("응답 메시지"),
                        fieldWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.grapes").description("받은 포도알 목록"),
                        fieldWithPath("data.grapes[].id").description("포도알 ID"),
                        fieldWithPath("data.grapes[].studyName").description("받은 포도알 목록"),
                        fieldWithPath("data.grapes[].compliment").description("받은 포도알 목록"),
                        fieldWithPath("data.grapes[].createdAt").description("받은 포도알 목록"),
                        fieldWithPath("data.pageInfo").description("페이지 메타 정보"),
                        fieldWithPath("data.pageInfo.totalPages").description("전체 페이지 수"),
                        fieldWithPath("data.pageInfo.totalElements").description("전체 요소 수"),
                        fieldWithPath("data.pageInfo.currentPage").description("현재 페이지"),
                        fieldWithPath("data.pageInfo.pageSize").description("페이지 크기")
                    )
                ));
        }
    }
}