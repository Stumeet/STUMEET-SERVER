package com.stumeet.server.notification.adapter.out.web;

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

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.notification.adapter.out.web.dto.RenewNotificationTokenRequest;
import com.stumeet.server.stub.NotificationStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class RenewNotificationTokenApiTest extends ApiTest {

    @Nested
    @DisplayName("알림 토큰 갱신 API")
    class RenewNotificationToken {
        private final String path = "/api/v1/notification-token/renew";

        @Test
        @WithMockMember
        @DisplayName("[성공] 알림 토큰 갱신에 성공한다.")
        void success() throws Exception {
            RenewNotificationTokenRequest request = NotificationStub.getRenewNotificationTokenRequest();

            mockMvc.perform(post(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isCreated())
                    .andDo(document("renew-notification-token/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("deviceId").description("디바이스 식별자"),
                                    fieldWithPath("notificationToken").description("알림 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지")
                            )));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 잘못된 형식으로 요청한 경우 알림 토큰 갱신에 실패한다.")
        void fail_when_invalid_format() throws Exception {
            RenewNotificationTokenRequest request = NotificationStub.getInvalidRenewNotificationTokenRequest();

            mockMvc.perform(post(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(request)))
                    .andExpect(status().isBadRequest())
                    .andDo(document("renew-notification-token/fail/invalid-format",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("deviceId").description("디바이스 식별자"),
                                    fieldWithPath("notificationToken").description("알림 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 상태"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data").description("에러 상세"),
                                    fieldWithPath("data[].message").description("에러 상세 메시지")
                            )));
        }
    }
}