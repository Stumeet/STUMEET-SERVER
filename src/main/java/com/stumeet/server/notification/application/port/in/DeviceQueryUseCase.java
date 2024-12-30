package com.stumeet.server.notification.application.port.in;

import java.util.List;

import com.stumeet.server.notification.domain.Device;

public interface DeviceQueryUseCase {

    List<Device> getMemberDevices(Long memberId);
}
