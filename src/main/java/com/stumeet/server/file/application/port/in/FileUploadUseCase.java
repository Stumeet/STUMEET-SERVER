package com.stumeet.server.file.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.stumeet.server.file.domain.FileUrl;

public interface FileUploadUseCase {

	FileUrl uploadUserProfileImage(int userId, MultipartFile multipartFile);

	FileUrl uploadStudyActivityImage(int studyId, List<MultipartFile> multipartFile);
}
