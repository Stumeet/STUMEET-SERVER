package com.stumeet.server.file.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.stumeet.server.file.application.port.out.FileUrl;

	public interface FileUploadUseCase {
	FileUrl uploadImage(MultipartFile multipartFile);

	FileUrl uploadUserProfileImage(Long userId, MultipartFile multipartFile);

	FileUrl uploadStudyActivityImage(Long studyId, List<MultipartFile> multipartFile);
}
