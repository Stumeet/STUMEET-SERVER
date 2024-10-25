package com.stumeet.server.notification.application.port.out;

import java.util.List;

import com.stumeet.server.notification.domain.Device;

public interface DeviceQueryPort {
    Device findTokenForMember(Long memberId, String deviceId);

    List<Device> findTokensForMember(Long memberId);
}
