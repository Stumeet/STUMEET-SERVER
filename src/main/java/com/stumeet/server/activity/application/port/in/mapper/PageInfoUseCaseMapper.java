package com.stumeet.server.activity.application.port.in.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.stumeet.server.activity.adapter.in.response.PageInfoResponse;

@Component
public class PageInfoUseCaseMapper {

	public <T> PageInfoResponse toPageInfoResponse(Page<T> items) {
		return PageInfoResponse.builder()
				.totalPages(items.getTotalPages())
				.totalElements(items.getTotalElements())
				.currentPage(items.getNumber())
				.pageSize(items.getSize())
				.build();
	}
}
