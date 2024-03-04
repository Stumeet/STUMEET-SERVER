package com.stumeet.server.file.adapter.in;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.stumeet.server.common.annotation.WebAdapter;
import com.stumeet.server.common.model.ApiResponse;
import com.stumeet.server.common.response.SuccessCode;
import com.stumeet.server.file.application.port.in.FileUploadUseCase;

import lombok.RequiredArgsConstructor;

@WebAdapter
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileUploadApi {

	private final FileUploadUseCase fileUploadUseCase;

	@PostMapping
	public ResponseEntity<ApiResponse<String>> uploadTestImage(
		@RequestPart("file")MultipartFile file
	) {
		return new ResponseEntity<>(
			ApiResponse.success(
				SuccessCode.FILE_UPLOAD_SUCCESS,
				fileUploadUseCase.uploadImage(file).url()),
			HttpStatus.CREATED);
	}
}
