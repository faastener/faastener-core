package org.faastener.core.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.domain.FilterConfiguration;
import org.faastener.core.model.entities.FilterConfigurationEntity;
import org.faastener.core.repositories.FilterConfigurationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterConfigurationServiceImpl implements FilterConfigurationService {
    private final FilterConfigurationRepository repository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<FilterConfiguration> findById(String id) {
        Optional<FilterConfigurationEntity> res = repository.findById(id);
        return res.map(entityMapper::toFilterConfigurationDomainModel);
    }

    @Override
    public List<FilterConfiguration> findAll() {
        List<FilterConfigurationEntity> res = repository.findAll();
        return res.stream()
                .map(entityMapper::toFilterConfigurationDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public FilterConfiguration save(FilterConfiguration filterConfiguration) {
        FilterConfigurationEntity saveResult = repository.save(entityMapper.toFilterConfigurationEntity(filterConfiguration));
        return entityMapper.toFilterConfigurationDomainModel(saveResult);
    }

    @Override
    public FilterConfiguration update(FilterConfiguration filterConfiguration) {
        return null;
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }
}
