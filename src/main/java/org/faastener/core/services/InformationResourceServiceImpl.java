package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.InformationSection;
import org.faastener.core.repositories.InformationResourceRepository;
import org.springframework.stereotype.Service;

@Service
public class InformationResourceServiceImpl implements InformationResourceService {
    private final InformationResourceRepository repository;

    public InformationResourceServiceImpl(InformationResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<InformationSection> findById(String id) {
        return this.repository.findById(id);
    }

    @Override
    public List<InformationSection> findAll() {
        return this.repository.findAll();
    }

    @Override
    public InformationSection save(InformationSection infoSection) {
        return this.repository.save(infoSection);
    }

    @Override
    public InformationSection update(InformationSection infoSection) {
        return this.repository.save(infoSection);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }
}
