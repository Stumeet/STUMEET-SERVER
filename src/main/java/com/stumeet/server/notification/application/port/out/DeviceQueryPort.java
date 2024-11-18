package com.stumeet.server.notification.application.port.out;

import java.util.List;

import com.stumeet.server.notification.domain.Device;

public interface DeviceQueryPort {
    Device findDeviceForMember(Long memberId, String deviceId);

    List<Device> findDevicesForMember(Long memberId);
}
