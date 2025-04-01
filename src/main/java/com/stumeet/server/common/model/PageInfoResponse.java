package com.stumeet.server.common.model;

import lombok.Builder;

@Builder
public record PageInfoResponse(
		int totalPages,
		long totalElements,
		int currentPage,
		int pageSize
) {
}
