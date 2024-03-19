package com.stumeet.server.member.application.service;

import com.stumeet.server.common.annotation.UseCase;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.member.application.port.in.MemberValidationUseCase;
import com.stumeet.server.member.application.port.out.MemberQueryPort;
import com.stumeet.server.member.domain.exception.MemberNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberValidationService implements MemberValidationUseCase {

    private final MemberQueryPort memberQueryPort;

    @Override
    public void validateNickname(String name) {
        if (memberQueryPort.isDuplicateNickname(name)) {
            throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME_EXCEPTION);
        }
    }

    @Override
    public void checkById(Long id) {
        if (!memberQueryPort.existsById(id)) {
            throw new MemberNotExistsException("존재하지 않는 멤버 입니다. 멤버 id : " + id, ErrorCode.MEMBER_NOT_FOUND);
        }
    }
}
