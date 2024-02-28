package com.stumeet.server.profession.application.port.out;

import com.stumeet.server.profession.domain.Profession;

import java.util.List;

public interface ProfessionQueryPort {
    Profession getById(Long id);

    List<Profession> findAll();
}
