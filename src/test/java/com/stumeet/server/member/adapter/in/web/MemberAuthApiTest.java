package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.member.adapter.out.persistence.JpaMemberRepository;
import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.member.application.port.in.TokenRenewCommand;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
class MemberAuthApiTest extends ApiTest {

    @Container
    @ServiceConnection
    private static GenericContainer<?> REDIS_CONTAINER = new GenericContainer<>(REDIS_CONTAINER_VERSION)
            .withExposedPorts(6379);

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private JpaMemberRepository jpaMemberRepository;

    @Nested
    @DisplayName("액세스 토큰 재발급")
    class tokenRenew {

        private final String path = "/api/v1/tokens";

        private String refreshToken;

        @BeforeEach
        void setUp() {
            MemberJpaEntity entity = jpaMemberRepository.save(MemberStub.getMemberEntity());
            refreshToken = jwtTokenProvider.generateRefreshToken(entity.getId());
            redisTemplate.opsForValue()
                    .set(TokenStub.getExpiredAccessToken(), refreshToken);
        }

        @AfterEach
        void tearDown() {
            redisTemplate.getConnectionFactory()
                    .getConnection()
                    .serverCommands()
                    .flushAll();
        }

        @Test
        @DisplayName("[성공] 액세스 토큰 재발급에 성공합니다.")
        void successTest() throws Exception {
            mockMvc.perform(post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(new TokenRenewCommand(TokenStub.getExpiredAccessToken(), refreshToken)))
                    ).andExpect(status().isOk())
                    .andDo(document("token_renew/success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestFields(
                                            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 액세스 토큰"),
                                            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 리프레시 토큰")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지"),
                                            fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("액세스 토큰")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("[실패] 액세스 토큰과 매칭되는 리프레시 토큰이 없는 경우 재발급에 실패합니다.")
        void notMatchAccessTokenTest() throws Exception {
            mockMvc.perform(post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(new TokenRenewCommand(TokenStub.getInvalidToken(), refreshToken)))
                    ).andExpect(status().isBadRequest())
                    .andDo(document("token_renew/fail/not-match-access-token",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestFields(
                                            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 액세스 토큰"),
                                            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 리프레시 토큰")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("[실패] 액세스 토큰이나 리프레시 토큰이 전달되지 않은 경우 재발급에 실패합니다.")
        void notExistRequestTest() throws Exception {
            mockMvc.perform(post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(new TokenRenewCommand("", null))
                            )).andExpect(status().isBadRequest())
                    .andDo(document("token_renew/fail/not-exist-request",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestFields(
                                    fieldWithPath("accessToken").description("서버로 부터 전달받은 액세스 토큰"),
                                    fieldWithPath("refreshToken").description("서버로 부터 전달받은 리프레시 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지"),
                                    fieldWithPath("data[].message").type(JsonFieldType.STRING).description("요청 실패 사유에 대한 메시지")
                            )
                    ));
        }

        @Test
        @DisplayName("[실패] 전달받은 리프레시 토큰이 서버의 리프레시 토큰과 다른 경우 재발급에 실패합니다.")
        void notMatchedRefreshTokenTest() throws Exception {
            refreshToken = jwtTokenProvider.generateRefreshToken(100L);

            mockMvc.perform(post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(new TokenRenewCommand(TokenStub.getExpiredAccessToken(), refreshToken)))
                    ).andExpect(status().isBadRequest())
                    .andDo(document("token_renew/fail/not-matched-refresh-token",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestFields(
                                            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 액세스 토큰"),
                                            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 리프레시 토큰")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                                    )
                            )
                    );
        }

        @Test
        @DisplayName("[실패] 리프레시 토큰이 만료된 경우 재발급에 실패합니다.")
        void expiredRefreshTokenTest() throws Exception {
            String expiredRefreshToken = TokenStub.getExpiredRefreshToken();
            String accessToken = TokenStub.getMockAccessToken();
            redisTemplate.opsForValue()
                    .set(accessToken, expiredRefreshToken);

            mockMvc.perform(post(path)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(new TokenRenewCommand(accessToken, expiredRefreshToken)))
                    ).andExpect(status().isBadRequest())
                    .andDo(document("token_renew/fail/expired-refresh-token",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestFields(
                                            fieldWithPath("accessToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 액세스 토큰"),
                                            fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("서버로 부터 전달받은 리프레시 토큰")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                                    )
                            )
                    );
        }
    }

}