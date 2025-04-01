package com.stumeet.server.study.application.port.in;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.study.application.port.in.command.StudyCreateCommand;

public interface StudyCreateUseCase {

	Long create(Long memberId, StudyCreateCommand command, MultipartFile mainImageFile);
}
