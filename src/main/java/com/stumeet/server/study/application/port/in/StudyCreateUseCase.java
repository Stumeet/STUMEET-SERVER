package com.stumeet.server.study.application.port.in;

import com.stumeet.server.member.domain.Member;
import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;

public interface StudyCreateUseCase {

	Long create(StudyCreateCommand command, Member member);
}
