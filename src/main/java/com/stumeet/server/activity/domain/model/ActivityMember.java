package com.stumeet.server.activity.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityMember {

    private Long id;

    private String name;

    private String image;

    @Override
    public String toString() {
        return "ActivityMember{" +
                "\n\tid=" + id +
                ",\n\tname='" + name + '\'' +
                ",\n\timage='" + image + '\'' +
                "\n}";
    }
}
