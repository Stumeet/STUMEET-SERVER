package com.stumeet.server.study.adapter.in.web;

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
import com.stumeet.server.helper.WithMockMember;
import com.stumeet.server.stub.StudyStub;
import com.stumeet.server.stub.TokenStub;
import com.stumeet.server.template.ApiTest;

class StudyDeleteApiTest extends ApiTest {

	@Nested
	@DisplayName("스터디 삭제 API")
	class DeleteStudy {

		@Test
		@WithMockMember
		@DisplayName("[성공] 스터디 삭제를 성공한다.")
		void successDeleteStudy() throws Exception {
			mockMvc.perform(delete("/api/v1/studies/{id}", StudyStub.getStudyId())
					.header(AuthenticationHeader.ACCESS_TOKEN.getName(), TokenStub.getMockAccessToken()))
				.andExpect(status().isOk())
				.andDo(document("delete-study/success",
					preprocessRequest(prettyPrint()),
					preprocessResponse(prettyPrint()),
					requestHeaders(headerWithName(AuthenticationHeader.ACCESS_TOKEN.getName()).description(
						"서버로부터 전달받은 액세스 토큰")),
					pathParameters(
						parameterWithName("id").description("스터디 ID")
					),
					responseFields(
						fieldWithPath("code").description("응답 상태"),
						fieldWithPath("message").description("응답 메시지")
					)));
		}
	}
}