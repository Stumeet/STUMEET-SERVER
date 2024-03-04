package com.stumeet.server.stub;

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
}
