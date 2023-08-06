package org.faastener.core.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.domain.ClassificationFramework;
import org.faastener.core.model.entities.ClassificationFrameworkEntity;
import org.faastener.core.repositories.ClassificationFrameworkRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassificationFrameworkServiceImpl implements ClassificationFrameworkService {
    private final ClassificationFrameworkRepository repository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<ClassificationFramework> findById(String id) {
        Optional<ClassificationFrameworkEntity> res = repository.findById(id);
        return res.map(entityMapper::toFrameworkDomainModel);
    }

    @Override
    public List<ClassificationFramework> findAll() {
        List<ClassificationFrameworkEntity> res = repository.findAll();
        return res.stream()
                .map(entityMapper::toFrameworkDomainModel)
                .collect(Collectors.toList());
    }

    @Override
    public ClassificationFramework save(ClassificationFramework classificationFramework) {
        ClassificationFrameworkEntity saveResult = repository.save(entityMapper.toFrameworkEntity(classificationFramework));
        return entityMapper.toFrameworkDomainModel(saveResult);
    }

    @Override
    public ClassificationFramework update(ClassificationFramework classificationFramework) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
