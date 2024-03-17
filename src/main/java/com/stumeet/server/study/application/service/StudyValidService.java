package com.stumeet.server.study.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.study.application.port.in.StudyValidUseCase;
import com.stumeet.server.study.application.port.out.StudyValidPort;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyValidService implements StudyValidUseCase {

    private final StudyValidPort studyValidPort;

    @Override
    public void checkById(Long id) {
        if (!studyValidPort.existsById(id)) {
            throw new StudyNotExistsException(id, ErrorCode.STUDY_NOT_FOUND);
        }
    }
}
