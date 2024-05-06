package com.stumeet.server.file.application.port.out;

import com.stumeet.server.file.application.port.dto.FileUrl;
import com.stumeet.server.file.domain.FileManagementPath;

public interface PresignedUrlGeneratePort {
    FileUrl generatePresignedUrl(FileManagementPath path, String identifier);

}
