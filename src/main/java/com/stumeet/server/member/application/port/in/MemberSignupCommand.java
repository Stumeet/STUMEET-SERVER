package com.stumeet.server.member.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record MemberSignupCommand(

        @NotNull
        MultipartFile image,

        @Size(min = 2, max = 10, message = "닉네임을 2 ~ 10자로 입력해주세요")
        String nickname,

        @NotBlank(message = "지역을 입력해주세요")
        String region,

        @NotNull(message = "분야를 선택해주세요")
        Long profession
) {
}
