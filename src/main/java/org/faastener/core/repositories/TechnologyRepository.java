package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.entities.TechnologyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends MongoRepository<TechnologyEntity, String>, TechnologyDossierSearch {
    Optional<TechnologyEntity> findById(String technologyId);
}
