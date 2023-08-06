package org.faastener.core.repositories;

import org.faastener.core.model.entities.InformationSectionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationResourceRepository extends MongoRepository<InformationSectionEntity, String> {

}
