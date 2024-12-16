package com.stumeet.server.notification.adapter.out.web;

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
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class NotificationLogQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("알림 기록 목록 조회 API")
    class GetNotificationLog {
        private static final String PATH = "/api/v1/notification/logs";

        @Test
        @WithMockMember
        @DisplayName("[성공] 멤버의 알림 기록 목록 조회에 성공한다.")
        void success() throws Exception {
            Integer size = 2;
            Integer page = 0;

            mockMvc.perform(get(PATH)
                    .param("size", size.toString())
                    .param("page", page.toString())
                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(SuccessCode.GET_SUCCESS.getMessage()))
                .andDo(document("get-member-notification-logs/success",
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
                        fieldWithPath("data.notificationLogs").description("알림 기록 목록"),
                        fieldWithPath("data.notificationLogs[].id").description("알림 ID"),
                        fieldWithPath("data.notificationLogs[].title").description("알림 제목"),
                        fieldWithPath("data.notificationLogs[].body").description("알림 내용"),
                        fieldWithPath("data.notificationLogs[].imgUrl").description("알림 이미지 URL"),
                        fieldWithPath("data.notificationLogs[].data").description("알림 메타 데이터"),
                        fieldWithPath("data.notificationLogs[].createdAt").description("알림 생성 시간"),
                        fieldWithPath("data.pageInfo").description("페이지 메타 정보"),
                        fieldWithPath("data.pageInfo.totalPages").description("전체 페이지 수"),
                        fieldWithPath("data.pageInfo.totalElements").description("전체 요소 수"),
                        fieldWithPath("data.pageInfo.currentPage").description("현재 페이지"),
                        fieldWithPath("data.pageInfo.pageSize").description("페이지 크기")
                    ).andWithPrefix("data.notificationLogs[].data.",
                        fieldWithPath("*").description("동적 메타 데이터 ex) type...")
                    )
                ));
        }
    }
}