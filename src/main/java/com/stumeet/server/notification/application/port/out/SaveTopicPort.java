package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.Topic;

public interface SaveTopicPort {

    Long save(Topic topic);
}
