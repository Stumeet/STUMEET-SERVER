package com.stumeet.server.file.adapter.in;

import com.stumeet.server.common.auth.model.AuthenticationHeader;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommands;
import com.stumeet.server.file.domain.exception.InvalidFileException;
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.FileStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PresignedUrlGenerateWebAdapterTest extends ApiTest {

    @Nested
    @DisplayName("PresignedUrl 생성")
    class GeneratePresignedUrl {

        private static final String PATH = "/api/v1/presigned-urls";

        @Test
        @WithMockMember
        @DisplayName("[성공] 해당하는 파일 목록에 대한 PresignedUrl 목록을 생성한다.")
        void successTest() throws Exception {
            PresignedUrlCommands commands = FileStub.getPresignedUrlCommands();

            mockMvc.perform(post(PATH)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(commands)))
                    .andExpect(status().isOk())
                    .andDo(document("presigned-urls-generate/success",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("commands[].path").description("해당하는 파일의 도메인"),
                                    fieldWithPath("commands[].fileName").description("파일 이름")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지"),
                                    fieldWithPath("data.presignedUrls[].url").description("Presigned url")
                            )
                    ));
        }

        @ParameterizedTest
        @MethodSource("com.stumeet.server.stub.FileStub#getInvalidFilesTestArguments")
        @WithMockMember
        @DisplayName("[실패] 파일 이름이 유효하지 않은 경우 예외가 발생합니다.")
        void invalidFileTest(String documentPath, PresignedUrlCommands invalidFileRequests, InvalidFileException e) throws Exception {
            mockMvc.perform(post(PATH)
                            .header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(toJson(invalidFileRequests)))
                    .andExpect(status().isBadRequest())
                    .andExpect(jsonPath("$.code").value(e.getErrorCode().getHttpStatusCode()))
                    .andExpect(jsonPath("$.message").value(e.getErrorCode().getMessage()))
                    .andDo(document(documentPath,
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint()),
                            requestHeaders(
                                    headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description("서버로부터 전달받은 액세스 토큰")
                            ),
                            requestFields(
                                    fieldWithPath("commands[].path").description("해당하는 파일의 도메인"),
                                    fieldWithPath("commands[].fileName").description("파일 이름")
                            ),
                            responseFields(
                                    fieldWithPath("code").description("응답 코드"),
                                    fieldWithPath("message").description("응답 메시지")
                            )
                    ));

        }
    }
}