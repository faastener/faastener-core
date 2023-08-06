package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.entities.ClassificationFrameworkEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationFrameworkRepository extends MongoRepository<ClassificationFrameworkEntity, String> {
    Optional<ClassificationFrameworkEntity> findById(String frameworkId);
}
