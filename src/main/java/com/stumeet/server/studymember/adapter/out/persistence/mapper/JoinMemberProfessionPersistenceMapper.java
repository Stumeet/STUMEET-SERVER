package com.stumeet.server.studymember.adapter.out.persistence.mapper;

import com.stumeet.server.studymember.adapter.out.persistence.entity.JoinMemberProfessionJpaEntity;
import com.stumeet.server.studymember.domain.JoinMemberProfession;
import org.springframework.stereotype.Component;

@Component
public class JoinMemberProfessionPersistenceMapper {

    public JoinMemberProfession toDomain(JoinMemberProfessionJpaEntity entity) {
        return JoinMemberProfession.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
