package com.stumeet.server.notification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Topic {

    private Long id;
    private String name;
    private String description;
    private TopicReferType referType;
    private Long referId;

    @Builder
    private Topic(Long id, String name, String description, TopicReferType referType, Long referId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.referType = referType;
        this.referId = referId;
    }
}
