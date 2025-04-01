package com.stumeet.server.report.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.report.adapter.in.request.ReportCreateRequest;
import com.stumeet.server.report.application.port.in.ReportAlertUseCase;
import com.stumeet.server.report.application.port.in.ReportCreateUseCase;
import com.stumeet.server.report.application.port.in.command.ReportCreateCommand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@WebAdapter
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportCreateApi {

    private final ReportCreateUseCase reportCreateUseCase;
    private final ReportAlertUseCase reportAlertUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<Void>> report(
            @RequestBody @Valid ReportCreateRequest request
    ) {
        ReportCreateCommand command = ReportCreateCommand.builder()
                .category(request.category())
                .reason(request.reason())
                .reporterId(request.reporterId())
                .reportedId(request.reportedId())
                .content(request.content())
                .build();

        Long reportId = reportCreateUseCase.report(command);
        reportAlertUseCase.alert(reportId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(SuccessCode.POST_SUCCESS));
    }
}
