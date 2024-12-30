package com.stumeet.server.studymember.adapter.out.persistence.mapper;

import com.stumeet.server.studymember.adapter.out.persistence.entity.JoinMemberJpaEntity;
import com.stumeet.server.studymember.domain.JoinMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JoinMemberPersistenceMapper {

    private final JoinMemberProfessionPersistenceMapper joinMemberProfessionPersistenceMapper;

    public JoinMember toDomain(JoinMemberJpaEntity entity) {
        return JoinMember.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .profession(joinMemberProfessionPersistenceMapper.toDomain(entity.getProfession()))
                .region(entity.getRegion())
                .build();
    }

    public JoinMemberJpaEntity toEntity(JoinMember domain) {
        return JoinMemberJpaEntity.builder()
                .id(domain.getId())
                .build();
    }
}
