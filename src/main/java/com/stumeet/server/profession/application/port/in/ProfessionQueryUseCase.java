package com.stumeet.server.profession.application.port.in;

import com.stumeet.server.profession.adapter.in.web.response.ProfessionResponses;
import com.stumeet.server.profession.domain.Profession;

public interface ProfessionQueryUseCase {
    Profession getById(Long id);

    ProfessionResponses findAll();
}
