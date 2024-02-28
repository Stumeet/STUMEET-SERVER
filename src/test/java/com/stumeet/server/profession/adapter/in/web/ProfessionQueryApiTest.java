package com.stumeet.server.profession.adapter.in.web;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
class ProfessionQueryApiTest extends ApiTest {

    @Nested
    @DisplayName("분야 정보 전체 조회")
    class GetProfessions {

        private final String path = "/api/v1/professions";

        @Test
        @WithMockUser
        @DisplayName("[성공] 분야 정보를 전체 조회할 수 있다.")
        void successTest() throws Exception{
            mockMvc.perform(get(path)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
                    .andExpect(status().isOk())
                    .andDo(document("find_professions/success",
                                    preprocessRequest(prettyPrint()),
                                    preprocessResponse(prettyPrint()),
                                    requestHeaders(
                                            headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                                    ),
                                    responseFields(
                                            fieldWithPath("code").type(JsonFieldType.NUMBER).description("응답에 대한 결과 코드"),
                                            fieldWithPath("message").type(JsonFieldType.STRING).description("응답에 대한 메시지"),
                                            fieldWithPath("data.professions[].id").type(JsonFieldType.NUMBER).description("분야 정보 ID"),
                                            fieldWithPath("data.professions[].name").type(JsonFieldType.STRING).description("분야 정보 이름")
                                    )
                            )
                    );
        }
    }
}