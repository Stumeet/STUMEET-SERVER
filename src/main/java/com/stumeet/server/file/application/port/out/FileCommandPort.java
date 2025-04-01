package com.stumeet.server.file.application.port.out;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.file.application.port.dto.FileUrl;

public interface FileCommandPort {

	FileUrl uploadImageFile(MultipartFile multipartFile, String directoryPath);

	List<FileUrl> uploadImageFiles(List<MultipartFile> multipartFileList, String directoryPath);

	void deleteFolder(String folder);
}