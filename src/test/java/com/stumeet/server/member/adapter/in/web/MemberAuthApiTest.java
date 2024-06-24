package com.stumeet.server.member.adapter.in.web;

import com.stumeet.server.common.token.JwtTokenProvider;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.member.adapter.out.persistence.JpaMemberRepository;
import com.stumeet.server.member.adapter.out.persistence.MemberJpaEntity;
import com.stumeet.server.member.application.port.in.command.MemberSignupCommand;
import com.stumeet.server.member.application.port.in.command.TokenRenewCommand;
import com.stumeet.server.member.domain.UserRole;
import com.stumeet.server.stub.MemberStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.InputStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    @DisplayName("토큰 재발급")
    class tokenRenew {

        private final String path = "/api/v1/tokens";

        private String refreshToken;

        @BeforeEach
        void setUp() {
            refreshToken = jwtTokenProvider.generateRefreshToken(MemberStub.getMember().getId());
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
        @DisplayName("[성공] 토큰 재발급에 성공합니다.")
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
                                            fieldWithPath("data.accessToken").type(JsonFieldType.STRING).description("액세스 토큰"),
                                            fieldWithPath("data.refreshToken").type(JsonFieldType.STRING).description("리프레시 토큰")
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

    @Nested
    @DisplayName("최초 로그인시 회원가입")
    class Signup {
        private final String path = "/api/v1/signup";

        @Test
        @WithMockMember(authority = UserRole.FIRST_LOGIN)
        @DisplayName("[성공] 최초 로그인시 회원가입에 성공합니다.")
        void successTest() throws Exception {
            MemberSignupCommand request = MemberStub.getMemberSignupCommand();

            mockMvc.perform(multipart(path)
                            .file((MockMultipartFile) request.image())
                            .header("Authorization", TokenStub.getMockAccessToken())
                            .queryParam("nickname", request.nickname())
                            .queryParam("region", request.region())
                            .queryParam("profession", String.valueOf(request.profession()))
                            .contentType(MediaType.MULTIPART_FORM_DATA)
                    ).andExpect(status().isOk())
                    .andDo(document("signup/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            queryParameters(
                                    parameterWithName("nickname").description("회원 닉네임"),
                                    parameterWithName("region").description("회원 지역"),
                                    parameterWithName("profession").description("회원 분야")
                            ),
                            requestParts(
                                    partWithName("image").description("회원 프로필 이미지")
                            ),
                            requestHeaders(
                                    headerWithName("Authorization").description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember
        @DisplayName("[실패] 이미 가입된 회원이 회원가입을 시도하는 경우 실패합니다.")
        void alreadySignupMemberFailTest() throws Exception {
            MemberSignupCommand request = MemberStub.getMemberSignupCommand();

            mockMvc.perform(multipart(path)
                            .file((MockMultipartFile) request.image())
                            .header("Authorization", TokenStub.getMockAccessToken())
                            .queryParam("nickname", request.nickname())
                            .queryParam("region", request.region())
                            .queryParam("profession", String.valueOf(request.profession()))
                            .contentType(MediaType.MULTIPART_FORM_DATA)
                    ).andExpect(status().isForbidden())
                    .andDo(document("signup/fail/already-signup-member",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            queryParameters(
                                    parameterWithName("nickname").description("회원 닉네임"),
                                    parameterWithName("region").description("회원 지역"),
                                    parameterWithName("profession").description("회원 분야")
                            ),
                            requestParts(
                                    partWithName("image").description("회원 프로필 이미지")
                            ),
                            requestHeaders(
                                    headerWithName("Authorization").description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(authority = UserRole.FIRST_LOGIN)
        @DisplayName("[실패] 회원가입 요청 값이 유효한 값이 아니면 회원가입에 실패합니다.")
        void invalidRequestTest() throws Exception {
            mockMvc.perform(multipart(path)
                            .file(new MockMultipartFile("1", (InputStream) null))
                            .header("Authorization", TokenStub.getMockAccessToken())
                            .queryParam("nickname", "")
                            .queryParam("region", "")
                            .queryParam("profession", "")
                            .contentType(MediaType.MULTIPART_FORM_DATA))
                    .andExpect(status().isBadRequest())
                    .andDo(document("signup/fail/invalid-request",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            queryParameters(
                                    parameterWithName("nickname").description("회원 닉네임"),
                                    parameterWithName("region").description("회원 지역"),
                                    parameterWithName("profession").description("회원 분야")
                            ),
                            requestHeaders(
                                    headerWithName("Authorization").description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지"),
                                    fieldWithPath("data[].message").type(JsonFieldType.STRING).description("요청 실패 사유에 대한 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(authority = UserRole.FIRST_LOGIN)
        @DisplayName("[실패] 전달한 분야 정보가 존재하지 않으면 회원가입에 실패합니다.")
        void notExistsProfessionTest() throws Exception {
            MemberSignupCommand request = MemberStub.getMemberSignupCommand();
            String notExistsProfession = "9999";

            mockMvc.perform(multipart(path)
                            .file((MockMultipartFile) request.image())
                            .header("Authorization", TokenStub.getMockAccessToken())
                            .queryParam("nickname", request.nickname())
                            .queryParam("region", request.region())
                            .queryParam("profession", notExistsProfession)
                            .contentType(MediaType.MULTIPART_FORM_DATA)
                    ).andExpect(status().isBadRequest())
                    .andDo(document("signup/fail/not-exists-profession",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            queryParameters(
                                    parameterWithName("nickname").description("회원 닉네임"),
                                    parameterWithName("region").description("회원 지역"),
                                    parameterWithName("profession").description("회원 분야")
                            ),
                            requestParts(
                                    partWithName("image").description("회원 프로필 이미지")
                            ),
                            requestHeaders(
                                    headerWithName("Authorization").description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                            )
                    ));
        }

        @Test
        @WithMockMember(authority = UserRole.FIRST_LOGIN)
        @DisplayName("[실패] 전달한 이미지가 유효한 이미지가 아니면 회원가입에 실패합니다.")
        void invalidImageTest() throws Exception {
            MemberSignupCommand request = MemberStub.getMemberSignupCommand();
            MockMultipartFile invalidImage = new MockMultipartFile("image", "test.jpa", "plain/text", "test".getBytes());

            mockMvc.perform(multipart(path)
                            .file(invalidImage)
                            .header("Authorization", TokenStub.getMockAccessToken())
                            .queryParam("nickname", request.nickname())
                            .queryParam("region", request.region())
                            .queryParam("profession", String.valueOf(request.profession()))
                            .contentType(MediaType.MULTIPART_FORM_DATA)
                    ).andExpect(status().isBadRequest())
                    .andDo(document("signup/fail/invalid-image",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            queryParameters(
                                    parameterWithName("nickname").description("회원 닉네임"),
                                    parameterWithName("region").description("회원 지역"),
                                    parameterWithName("profession").description("회원 분야")
                            ),
                            requestParts(
                                    partWithName("image").description("회원 프로필 이미지")
                            ),
                            requestHeaders(
                                    headerWithName("Authorization").description("서버로부터 전달받은 액세스 토큰")
                            ),
                            responseFields(
                                    fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                    fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지")
                            )
                    ));

        }
    }
}