package org.faastener.core.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.domain.InformationSection;
import org.faastener.core.model.entities.InformationSectionEntity;
import org.faastener.core.repositories.InformationResourceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformationResourceServiceImpl implements InformationResourceService {
    private final InformationResourceRepository repository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<InformationSection> findById(String id) {
        Optional<InformationSectionEntity> res = repository.findById(id);
        return res.map(entityMapper::toInformationSectionDomainModel);
    }

    @Override
    public List<InformationSection> findAll() {
        List<InformationSectionEntity> res = repository.findAll();
        return res.stream()
                .map(entityMapper::toInformationSectionDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public InformationSection save(InformationSection infoSection) {
        InformationSectionEntity saveResult = repository.save(entityMapper.toInformationSectionEntity(infoSection));
        return entityMapper.toInformationSectionDomainModel(saveResult);
    }

    @Override
    public InformationSection update(InformationSection infoSection) {
        return null;
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }
}
