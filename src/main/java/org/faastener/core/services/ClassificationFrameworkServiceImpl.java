package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.repositories.ClassificationFrameworkRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassificationFrameworkServiceImpl implements ClassificationFrameworkService {
    private final ClassificationFrameworkRepository repository;

    public ClassificationFrameworkServiceImpl(ClassificationFrameworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ClassificationFramework> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<ClassificationFramework> findAll() {
        return repository.findAll();
    }

    @Override
    public ClassificationFramework save(ClassificationFramework classificationFramework) {
        return null;
    }

    @Override
    public ClassificationFramework update(ClassificationFramework classificationFramework) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
