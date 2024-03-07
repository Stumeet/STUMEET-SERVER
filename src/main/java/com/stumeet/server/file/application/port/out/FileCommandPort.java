package com.stumeet.server.file.application.port.out;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileCommandPort {
	FileUrl uploadImageFile(MultipartFile multipartFile, String directoryPath);

	List<FileUrl> uploadImageFiles(List<MultipartFile> multipartFileList, String directoryPath);
}