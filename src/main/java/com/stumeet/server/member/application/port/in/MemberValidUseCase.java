package com.stumeet.server.member.application.port.in;

public interface MemberValidUseCase {

    void validateNickname(String name);

    void checkById(Long id);
}
