package com.stumeet.server.activity.application.port.in.command;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

import com.stumeet.server.common.annotation.validator.NullOrNotBlank;

@Builder
public record ActivityCreateCommand(
        @NotBlank(message = "활동 카테고리를 입력해주세요")
        String category,

        @NotBlank(message = "활동 제목을 입력해주세요")
        @Size(max = 100, message = "활동 제목은 100자 이하여야 합니다")
        String title,

        @NotBlank(message = "활동 내용을 입력해주세요")
        @Size(max = 500, message = "활동 내용은 500자 이하여야 합니다")
        String content,

        @NotNull(message = "이미지 리스트를 전달해주세요")
        @Size(max = 5, message = "이미지는 5개 이하로 등록할 수 있습니다")
        List<String> images,

        boolean isNotice,

        @Nullable
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime startDate,

        @Nullable
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime endDate,

        @NullOrNotBlank
        String location,

        @NullOrNotBlank
        String link,

        @NotNull(message = "참여 멤버 리스트를 전달해주세요")
        List<Long> participants

) {
}
