package com.stumeet.server.common.alert;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.stumeet.server.report.application.port.out.ReportAlertPort;
import com.stumeet.server.report.application.port.out.dto.ReportRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DiscordReportAlertAdapter implements ReportAlertPort {

    private final DiscordClient discordClient;

    @Override
    public void report(ReportRequest request) {
        Map<String, String> payload = Map.of(
                "content", request.content(),
                "username", request.username());

        discordClient.sendMessage(payload);
    }
}
