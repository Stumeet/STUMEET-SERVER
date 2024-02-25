package com.stumeet.server.common.auth.filter;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.template.ApiTest;
import com.stumeet.server.template.RedisTestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.cloud.contract.spec.internal.MediaTypes.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WireMockTest(httpPort = 8089)
@Import(RedisTestConfig.class)
@Transactional
class OAuthAuthenticationFilterTest extends ApiTest {

    @Nested
    @DisplayName("OAuth2를 이용한 소셜 로그인 필터")
    class oauthLogin {
        private final String path = "/api/v1/oauth";

        private static void mockKakaoTokenInfoApi() {
            stubFor(
                    WireMock.get(WireMock.urlEqualTo("/v1/user/access_token_info"))
                            .withHeader(AuthenticationHeader.ACCESS_TOKEN.getName(), equalTo("rjdizj7Ae09H0oWlW46Oll9_x7AhzaJkp1gKKwzTAAABjd_1h0EhI_W2iN1234"))
                            .willReturn(aResponse()
                                    .withStatus(HttpStatus.CREATED.value())
                                    .withHeader("content-type", APPLICATION_JSON)
                                    .withBody(MemberStub.getKakaoAccessTokenInfo())
                            )
            );
        }

        @Test
        @DisplayName("[성공] 소셜 로그인 성공")
        void successTest() throws Exception {
            mockKakaoTokenInfoApi();

            MvcResult result = mockMvc.perform(
                            post(path)
                                    .header(AuthenticationHeader.ACCESS_TOKEN.getName(), "rjdizj7Ae09H0oWlW46Oll9_x7AhzaJkp1gKKwzTAAABjd_1h0EhI_W2iN1234")
                                    .header(AuthenticationHeader.X_OAUTH_PROVIDER.getName(), "kakao")
                    ).andExpect(status().isOk())
                    .andReturn();
        }
    }
}