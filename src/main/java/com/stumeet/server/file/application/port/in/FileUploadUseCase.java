package com.stumeet.server.file.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.stumeet.server.file.application.port.dto.FileUrl;

	public interface FileUploadUseCase {

	FileUrl uploadUserProfileImage(Long userId, MultipartFile multipartFile);

	FileUrl uploadStudyMainImage(Long studyId, MultipartFile multipartFile);

	List<FileUrl> uploadStudyActivityImage(Long studyId, List<MultipartFile> multipartFile);
}
