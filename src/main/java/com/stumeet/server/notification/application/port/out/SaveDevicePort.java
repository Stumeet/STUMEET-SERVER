package com.stumeet.server.notification.application.port.out;

import com.stumeet.server.notification.domain.Device;

public interface SaveDevicePort {
    void save(Device device);
}
