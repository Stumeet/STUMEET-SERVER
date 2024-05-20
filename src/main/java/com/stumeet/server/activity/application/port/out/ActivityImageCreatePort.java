package com.stumeet.server.activity.application.port.out;

import com.stumeet.server.activity.domain.model.ActivityImage;

import java.util.List;

public interface ActivityImageCreatePort {
    void create(List<ActivityImage> images);
}
