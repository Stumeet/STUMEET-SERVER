package com.stumeet.server.common.auth.filter;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.member.domain.OAuthProvider;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.cloud.contract.spec.internal.MediaTypes.APPLICATION_JSON;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WireMockTest(httpPort = 8089)
class OAuthAuthenticationFilterTest extends ApiTest {

    @Container
    @ServiceConnection
    private static GenericContainer<?> REDIS_CONTAINER = new GenericContainer<>(REDIS_CONTAINER_VERSION)
            .withExposedPorts(6379);

    @Nested
    @DisplayName("OAuth2를 이용한 소셜 로그인")
    class oauthLogin {
        private final String path = "/api/v1/oauth";


        @Test
        @DisplayName("[성공] 소셜 로그인(카카오)에 성공합니다.")
        void successKakaoTest() throws Exception {
            mockSuccessKakaoTokenInfoApi();

            mockMvc.perform(post(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getKakaoAccessToken())
                            .header(AuthenticationHeader.X_OAUTH_PROVIDER.getName(), OAuthProvider.KAKAO.getProvider()))
                    .andExpect(status().isOk())
                    .andDo(document("social_login/success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("OAuth Provider로 부터 전달받은 토큰"),
                                            headerWithName(AuthenticationHeader.X_OAUTH_PROVIDER.getName()).description("OAuth Provider 이름")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지"),
                                            fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("액세스 토큰"),
                                            fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레시 토큰"),
                                            fieldWithPath("data.isFirstLogin").type(JsonFieldType.BOOLEAN).description("최초 로그인 여부")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("[실패] 잘못된 토큰을 전달하는 경우 인증이 실패합니다.")
        void invalidAccessTokenTest() throws Exception {
            mockFailKakaoTokenInfoApi();

            mockMvc.perform(post(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getInvalidToken())
                            .header(AuthenticationHeader.X_OAUTH_PROVIDER.getName(), OAuthProvider.KAKAO.getProvider()))
                    .andExpect(status().isUnauthorized())
                    .andDo(document("social_login/fail/invalid-token",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("OAuth Provider로 부터 전달받은 토큰"),
                                            headerWithName(AuthenticationHeader.X_OAUTH_PROVIDER.getName()).description("OAuth Provider 이름")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("[실패] 인증 정보를 헤더에 포함하지 않는 경우 인증에 실패합니다.")
        void notExistHeaderTest() throws Exception {
            mockMvc.perform(post(path))
                    .andExpect(status().isUnauthorized())
                    .andDo(document("social_login/fail/not-exist-header",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("[실패] 잘못 서명된 토큰을 전달하는 경우 인증에 실패합니다.")
        void invalidSignatureTest() throws Exception {
            mockFailAppleInvalidSignatureTokenInfoApi();

            mockMvc.perform(post(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getInvalidSignatureAccessToken())
                            .header(AuthenticationHeader.X_OAUTH_PROVIDER.getName(), OAuthProvider.APPLE.getProvider()))
                    .andExpect(status().isUnauthorized())
                    .andDo(document("social_login/fail/invalid-signature",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("OAuth Provider로 부터 전달받은 토큰"),
                                            headerWithName(AuthenticationHeader.X_OAUTH_PROVIDER.getName()).description("OAuth Provider 이름")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                                    )
                            )
                    );
        }

        private void mockFailKakaoTokenInfoApi() {
            stubFor(
                    WireMock.get(WireMock.urlEqualTo("/v1/user/access_token_info"))
                            .withHeader(AuthenticationHeader.ACCESS_TOKEN.getName(), equalTo(TokenStub.getInvalidToken()))
                            .willReturn(aResponse()
                                    .withStatus(HttpStatus.UNAUTHORIZED.value())
                                    .withHeader("content-type", APPLICATION_JSON)
                                    .withBody(MemberStub.getInvalidKakaoAccessTokenInfo())
                            )
            );
        }

        private void mockSuccessKakaoTokenInfoApi() {
            stubFor(
                    WireMock.get(WireMock.urlEqualTo("/v1/user/access_token_info"))
                            .withHeader(AuthenticationHeader.ACCESS_TOKEN.getName(), equalTo(TokenStub.getKakaoAccessToken()))
                            .willReturn(aResponse()
                                    .withStatus(HttpStatus.CREATED.value())
                                    .withHeader("content-type", APPLICATION_JSON)
                                    .withBody(MemberStub.getKakaoAccessTokenInfo())
                            )
            );
        }

        private void mockFailAppleInvalidSignatureTokenInfoApi() {
            stubFor(
                    WireMock.get(WireMock.urlEqualTo("/auth/keys"))
                            .willReturn(aResponse()
                                    .withStatus(HttpStatus.OK.value())
                                    .withHeader("content-type", APPLICATION_JSON)
                                    .withBody(MemberStub.getAppleInvalidSignatureTokenInfo())
                            )
            );
        }
    }
}