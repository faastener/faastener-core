package org.faastener.core.repositories;

import java.util.Collection;
import java.util.Optional;

import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyDossierRepository extends MongoRepository<TechnologyDossierEntity, String>, TechnologyDossierSearch {
    Optional<TechnologyDossierEntity> findById(String technologyId);

    Streamable<TechnologyDossierEntity> findAllByTechnologyIdIn(Collection<String> ids);
}
