package com.stumeet.server.study.application.port.out;

import java.util.List;

import com.stumeet.server.study.domain.Study;

public interface StudyQueryPort {

	Study getById(Long id);

	List<Study> getMemberRecentActiveStudies(Long memberId);
}
