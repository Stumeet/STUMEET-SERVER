package com.stumeet.server.studymember.application.port.in.mapper;

import com.stumeet.server.studymember.domain.JoinMember;
import org.springframework.stereotype.Component;

@Component
public class JoinMemberUseCaseMapper {
    public JoinMember toDomain(Long id) {
        return JoinMember.builder()
                .id(id)
                .build();
    }
}
