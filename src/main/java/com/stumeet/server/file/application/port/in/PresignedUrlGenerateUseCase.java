package com.stumeet.server.file.application.port.in;

import com.stumeet.server.file.application.port.in.command.PresignedUrlCommand;
import com.stumeet.server.file.application.port.in.response.PresignedUrlResponse;

public interface PresignedUrlGenerateUseCase {

    PresignedUrlResponse generatePresignedUrl(PresignedUrlCommand request);
}
