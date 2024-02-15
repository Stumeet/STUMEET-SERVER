package com.stumeet.server.member.adapter.out.persistence;

import com.stumeet.server.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberPersistenceMapper {

    public MemberJpaEntity toEntity(Member domain) {
        return MemberJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .image(domain.getImage())
                .sugarContents(domain.getSugarContents())
                .region(domain.getRegion())
                .profession(domain.getProfession())
                .authType(domain.getAuthType())
                .role(domain.getRole())
                .build();
    }

    public Member toDomain(MemberJpaEntity entity) {
        return Member.builder()
                .id(entity.getId())
                .name(entity.getName())
                .image(entity.getImage())
                .sugarContents(entity.getSugarContents())
                .region(entity.getRegion())
                .profession(entity.getProfession())
                .authType(entity.getAuthType())
                .role(entity.getRole())
                .build();
    }
}
