package com.stumeet.server.stub;

import com.stumeet.server.profession.adapter.out.persistence.ProfessionJpaEntity;
import com.stumeet.server.profession.domain.Profession;

public class ProfessionStub {

    private ProfessionStub() {
    }

    public static Profession getProfession() {
        return Profession.builder()
                .id(1L)
                .name("경영사무")
                .parent(null)
                .build();
    }

    public static ProfessionJpaEntity getProfessionEntity() {
        return ProfessionJpaEntity.builder()
                .id(1L)
                .name("경영사무")
                .parent(null)
                .build();
    }
}
