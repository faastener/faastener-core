package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.TechnologyDossier;
import org.faastener.core.repositories.TechnologyDossierRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnologyDossierServiceImpl implements TechnologyDossierService {
    private final TechnologyDossierRepository repository;

    public TechnologyDossierServiceImpl(TechnologyDossierRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<TechnologyDossier> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<TechnologyDossier> findAll() {
        return repository.findAll();
    }

    @Override
    public TechnologyDossier save(TechnologyDossier technologyDossier) {
        return null;
    }

    @Override
    public TechnologyDossier update(TechnologyDossier technologyDossier) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
