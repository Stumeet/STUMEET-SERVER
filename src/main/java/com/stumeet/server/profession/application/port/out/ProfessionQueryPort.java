package com.stumeet.server.profession.application.port.out;

import com.stumeet.server.profession.domain.Profession;

public interface ProfessionQueryPort {
    Profession getById(Long id);
}
