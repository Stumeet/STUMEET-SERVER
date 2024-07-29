package com.stumeet.server.file.adapter.in;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.file.application.port.in.PresignedUrlGenerateUseCase;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.adapter.in.response.PresignedUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@WebAdapter
@RequestMapping("/api/v1/presigned-url")
@RequiredArgsConstructor
public class PresignedUrlGenerateWebAdapter {

    private final PresignedUrlGenerateUseCase presignedUrlGenerateUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<PresignedUrlResponse>> generatePresignedUrl(
            @RequestBody PresignedUrlCommand request
    ) {
        PresignedUrlResponse response = presignedUrlGenerateUseCase.generatePresignedUrl(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(SuccessCode.PRESIGNED_URL_SUCCESS, response));
    }
}
