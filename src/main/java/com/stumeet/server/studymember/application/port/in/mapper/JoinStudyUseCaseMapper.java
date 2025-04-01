package com.stumeet.server.studymember.application.port.in.mapper;

import com.stumeet.server.studymember.domain.JoinStudy;
import org.springframework.stereotype.Component;

@Component
public class JoinStudyUseCaseMapper {

    public JoinStudy toDomain(Long id) {
        return JoinStudy.builder()
                .id(id)
                .build();
    }
}
