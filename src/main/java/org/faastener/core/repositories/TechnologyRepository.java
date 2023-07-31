package org.faastener.core.repositories;

import java.util.Optional;

import org.faastener.core.model.Technology;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends MongoRepository<Technology, String>, TechnologyDossierSearch {
    Optional<Technology> findById(String technologyId);
}
