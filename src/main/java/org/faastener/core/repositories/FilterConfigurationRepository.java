package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.entities.FilterConfigurationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterConfigurationRepository extends MongoRepository<FilterConfigurationEntity, String> {
    Optional<FilterConfigurationEntity> findById(String technologyId);
}
