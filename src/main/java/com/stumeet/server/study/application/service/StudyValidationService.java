package com.stumeet.server.study.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.application.port.out.StudyValidationPort;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyValidationService implements StudyValidationUseCase {

    private final StudyValidationPort studyValidationPort;

    @Override
    public void checkById(Long id) {
        if (!studyValidationPort.existsById(id)) {
            throw new StudyNotExistsException(id);
        }
    }
}
