package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.TechnologyDossier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyDossierRepository extends MongoRepository<TechnologyDossier, String>, TechnologyDossierSearch {
    Optional<TechnologyDossier> findById(String technologyId);
}
