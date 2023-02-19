package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.FilterConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterConfigurationRepository extends MongoRepository<FilterConfiguration, String> {
    Optional<FilterConfiguration> findById(String technologyId);
}
