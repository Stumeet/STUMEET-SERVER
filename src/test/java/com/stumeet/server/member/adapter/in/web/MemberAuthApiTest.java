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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
class MemberAuthApiTest extends ApiTest {

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

    }

}