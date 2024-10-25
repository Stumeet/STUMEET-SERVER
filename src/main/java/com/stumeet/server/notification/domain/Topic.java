package com.stumeet.server.notification.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Topic {

    private Long id;
    private String name;
    private String description;
    private TopicType type;
    private Long referId;

    @Builder
    private Topic(Long id, String name, String description, TopicType type, Long referId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.referId = referId;
    }
}
