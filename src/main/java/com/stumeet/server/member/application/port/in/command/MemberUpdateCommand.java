package com.stumeet.server.member.application.port.in.command;

import com.stumeet.server.common.annotation.validator.NullOrImageFile;
import com.stumeet.server.common.annotation.validator.NullOrNotBlank;
import com.stumeet.server.common.annotation.validator.NullOrPositive;
import com.stumeet.server.common.annotation.validator.NullOrValidSize;
import org.springframework.web.multipart.MultipartFile;

public record MemberUpdateCommand(
        @NullOrImageFile(message = "이미지 파일을 첨부해주세요")
        MultipartFile image,

        @NullOrValidSize(min = 2, max = 10, message = "닉네임을 2 ~ 10자로 입력해주세요")
        String nickname,

        @NullOrNotBlank(message = "지역을 입력해주세요")
        String region,

        @NullOrPositive(message = "존재하지 않는 분야입니다.")
        Long profession
) {
}
