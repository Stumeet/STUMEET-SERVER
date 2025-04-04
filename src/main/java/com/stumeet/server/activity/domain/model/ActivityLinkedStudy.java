package com.stumeet.server.activity.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class ActivityLinkedStudy {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return "ActivityLinkedStudy{" +
                "\n\tid=" + id +
                "\n}";
    }
}
