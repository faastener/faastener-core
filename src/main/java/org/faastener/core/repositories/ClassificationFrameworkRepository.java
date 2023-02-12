package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.ClassificationFramework;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationFrameworkRepository extends MongoRepository<ClassificationFramework, String> {
    Optional<ClassificationFramework> findById(String frameworkId);
}
