package com.stumeet.server.dev.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.dev.adapter.in.web.response.AccessTokenResponse;
import com.stumeet.server.dev.application.service.DevService;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/dev")
@RequiredArgsConstructor
public class DevApi {

	private final DevService devService;

	@PostMapping("/access-token")
	public ApiResponse<AccessTokenResponse> getAccessToken(
			@RequestParam(name = "memberId") Long memberId
	) {
		return ApiResponse.success(SuccessCode.POST_SUCCESS, devService.getAccessToken(memberId));
	}
}
