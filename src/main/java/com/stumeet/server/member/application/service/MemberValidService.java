package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.member.application.port.in.MemberValidUseCase;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberValidService implements MemberValidUseCase {

    private final MemberQueryPort memberQueryPort;

    @Override
    public void validateNickname(String name) {
        if (memberQueryPort.isDuplicateNickname(name)) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME_EXCEPTION);
        }
    }
}
