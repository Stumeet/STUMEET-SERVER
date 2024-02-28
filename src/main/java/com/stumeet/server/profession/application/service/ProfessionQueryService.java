package com.stumeet.server.profession.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.profession.application.port.in.ProfessionQueryUseCase;
import com.stumeet.server.profession.application.port.out.ProfessionQueryPort;
import com.stumeet.server.profession.domain.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessionQueryService implements ProfessionQueryUseCase {

    private final ProfessionQueryPort professionQueryPort;

    @Override
    public Profession getById(Long id) {
        return professionQueryPort.getById(id);
    }
}
