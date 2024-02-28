package com.stumeet.server.profession.adapter.out.persistence;

import com.stumeet.server.common.annotation.PersistenceAdapter;
import com.stumeet.server.common.exception.model.BusinessException;
import com.stumeet.server.common.response.ErrorCode;
import com.stumeet.server.profession.application.port.out.ProfessionQueryPort;
import com.stumeet.server.profession.domain.Profession;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProfessionPersistenceAdapter implements ProfessionQueryPort {
    private final ProfessionPersistenceMapper professionPersistenceMapper;
    private final JpaProfessionRepository jpaProfessionRepository;

    @Override
    public Profession getById(Long id) {
        ProfessionJpaEntity entity = jpaProfessionRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_EXCEPTION));

        return professionPersistenceMapper.toDomain(entity);
    }

    @Override
    public List<Profession> findAll() {
        return jpaProfessionRepository.findAll().stream()
                .map(professionPersistenceMapper::toDomain)
                .toList();
    }
}
