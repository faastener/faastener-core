package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.ClassificationFramework;
import org.springframework.stereotype.Service;

@Service
public class ClassificationFrameworkServiceImpl implements ClassificationFrameworkService {
    @Override
    public Optional<ClassificationFramework> findById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<ClassificationFramework> findByProductId(Integer productId) {
        return Optional.empty();
    }

    @Override
    public List<ClassificationFramework> findAll() {
        return null;
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
