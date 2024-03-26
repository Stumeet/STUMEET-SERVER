package com.stumeet.server.member.application.port.in;

public interface MemberValidationUseCase {

    void validateNickname(String name);

    void checkById(Long id);
}
