package com.stumeet.server.member.application.port.in;

import jakarta.validation.constraints.NotBlank;

public record MemberSignupCommand(
        @NotBlank(message = "지역을 입력해주세요")
        String region,

        @NotBlank(message = "분야를 선택해주세요")
        String profession
) {
}
