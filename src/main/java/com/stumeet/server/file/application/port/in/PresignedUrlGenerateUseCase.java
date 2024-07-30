package com.stumeet.server.file.application.port.in;

import com.stumeet.server.file.adapter.in.response.PresignedUrlResponses;
import com.stumeet.server.file.application.port.in.command.PresignedUrlCommands;

public interface PresignedUrlGenerateUseCase {
    PresignedUrlResponses generatePresignedUrls(PresignedUrlCommands commands);
}
