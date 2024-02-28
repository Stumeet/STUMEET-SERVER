package com.stumeet.server.profession.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Profession {

    private Long id;

    private String name;

    private Profession parent;

}
