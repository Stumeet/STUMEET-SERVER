package com.stumeet.server.common.auth.filter;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.common.util.JwtUtil;
import com.stumeet.server.member.adapter.out.persistence.JpaMemberRepository;
import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class LogoutFilterTest extends ApiTest {

    @Container
    @ServiceConnection
    private static GenericContainer<?> REDIS_CONTAINER = new GenericContainer<>(REDIS_CONTAINER_VERSION)
            .withExposedPorts(6379);

    @Autowired
    private JpaMemberRepository jpaMemberRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Nested
    @DisplayName("로그아웃")
    class Logout {

        private final String path = "/api/v1/logout";

        @BeforeEach
        void setUp() {
            MemberJpaEntity entity = jpaMemberRepository.save(MemberStub.getMemberEntity());
            redisTemplate.opsForValue()
                    .set(
                            JwtUtil.resolveToken(TokenStub.getMockAccessToken()),
                            jwtTokenProvider.generateRefreshToken(entity.getId())
                    );
        }

        @Test
        @DisplayName("[성공] 로그아웃에 성공합니다.")
        void successTest() throws Exception {
            mockMvc.perform(post(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("logout/success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
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
