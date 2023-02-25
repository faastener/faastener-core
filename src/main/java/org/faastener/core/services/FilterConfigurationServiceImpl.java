package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.FilterConfiguration;
import org.springframework.stereotype.Service;

@Service
public class FilterConfigurationServiceImpl implements FilterConfigurationService {
    @Override
    public Optional<FilterConfiguration> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<FilterConfiguration> findAll() {
        return null;
    }

    @Override
    public FilterConfiguration save(FilterConfiguration filterConfiguration) {
        return null;
    }

    @Override
    public FilterConfiguration update(FilterConfiguration filterConfiguration) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
