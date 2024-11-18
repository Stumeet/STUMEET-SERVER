package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.Topic;

public interface SaveTopicPort {

    void save(Topic topic);
}
