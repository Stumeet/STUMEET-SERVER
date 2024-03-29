package com.stumeet.server.member.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record MemberSignupCommand(

        @NotNull(message = "이미지를 첨부해주세요")
        MultipartFile image,

        @Size(min = 2, max = 10, message = "닉네임을 2 ~ 10자로 입력해주세요")
        String nickname,

        @NotBlank(message = "지역을 입력해주세요")
        String region,

        @Positive(message = "분야를 선택해주세요")
        Long profession
) {
}
