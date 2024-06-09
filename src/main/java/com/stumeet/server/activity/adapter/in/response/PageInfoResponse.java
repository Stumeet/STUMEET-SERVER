package com.stumeet.server.activity.adapter.in.response;

import lombok.Builder;

@Builder
public record PageInfoResponse(
		int totalPages,
		long totalElements,
		int currentPage,
		int pageSize
) {
}
