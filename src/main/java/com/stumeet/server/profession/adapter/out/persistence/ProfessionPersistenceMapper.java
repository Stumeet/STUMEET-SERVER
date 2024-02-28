package com.stumeet.server.profession.adapter.out.persistence;

import com.stumeet.server.profession.domain.Profession;
import org.springframework.stereotype.Component;

@Component
public class ProfessionPersistenceMapper {

    public ProfessionJpaEntity toEntity(Profession domain) {
        if (domain == null) {
            return null;
        }
        ProfessionJpaEntity parent = null;

        if (domain.getParent() != null) {
            parent = ProfessionJpaEntity.builder()
                    .id(domain.getId())
                    .name(domain.getName())
                    .parent(null)
                    .build();
        }

        return ProfessionJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .parent(parent)
                .build();
    }

    public Profession toDomain(ProfessionJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        Profession parent = null;

        if (entity.getParent() != null) {
            parent = Profession.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .parent(null)
                    .build();
        }

        return Profession.builder()
                .id(entity.getId())
                .name(entity.getName())
                .parent(parent)
                .build();
    }
}
