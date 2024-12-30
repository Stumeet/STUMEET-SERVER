package com.stumeet.server.notification.application.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.notification.application.port.in.DeviceQueryUseCase;
import com.stumeet.server.notification.application.port.out.DeviceQueryPort;
import com.stumeet.server.notification.domain.Device;

import lombok.RequiredArgsConstructor;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberDeviceQueryService implements DeviceQueryUseCase {
    private final DeviceQueryPort deviceQueryPort;

    @Override
    public List<Device> getMemberDevices(Long memberId) {
        return deviceQueryPort.findDevicesForMember(memberId);
    }
}
