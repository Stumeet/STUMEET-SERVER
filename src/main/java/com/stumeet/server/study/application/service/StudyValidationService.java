package com.stumeet.server.study.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.exception.model.InvalidStateException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.study.application.port.in.StudyValidationUseCase;
import com.stumeet.server.study.application.port.out.StudyQueryPort;
import com.stumeet.server.study.application.port.out.StudyValidationPort;
import com.stumeet.server.study.domain.Study;
import com.stumeet.server.study.domain.exception.StudyNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudyValidationService implements StudyValidationUseCase {

    private final StudyValidationPort studyValidationPort;
    private final StudyQueryPort studyQueryPort;

    @Override
    public void checkById(Long id) {
        if (!studyValidationPort.existsById(id)) {
            throw new StudyNotExistsException(id);
        }
    }

    @Override
    public void checkLegacyStudy(Long id) {
        Study study = studyQueryPort.getById(id);

        if (study.isDeleted()) {
            throw new InvalidStateException(ErrorCode.ALREADY_DELETED_STUDY);
        }

        if (!study.isFinished()) {
            throw new InvalidStateException(ErrorCode.NOT_YET_FINISHED_STUDY);
        }
    }
}
