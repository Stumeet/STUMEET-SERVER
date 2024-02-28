package com.stumeet.server.profession.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.profession.application.port.in.ProfessionQueryUseCase;
import com.stumeet.server.profession.adapter.in.web.response.ProfessionResponse;
import com.stumeet.server.profession.adapter.in.web.response.ProfessionResponses;
import com.stumeet.server.profession.application.port.out.ProfessionQueryPort;
import com.stumeet.server.profession.domain.Profession;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProfessionQueryService implements ProfessionQueryUseCase {

    private final ProfessionQueryPort professionQueryPort;

    @Override
    public Profession getById(Long id) {
        return professionQueryPort.getById(id);
    }

    @Override
    public ProfessionResponses findAll() {
        List<ProfessionResponse> professions = professionQueryPort.findAll().stream()
                .map(profession -> new ProfessionResponse(profession.getId(), profession.getName()))
                .toList();

        return new ProfessionResponses(professions);
    }
}
