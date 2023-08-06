package org.faastener.core.repositories;

import java.util.List;

import org.faastener.core.model.entities.search.SearchCriterion;
import org.faastener.core.model.entities.TechnologyDossierEntity;

public interface TechnologyDossierSearch {
    List<TechnologyDossierEntity> searchDossiers(List<SearchCriterion> params);

    List<String> searchDossierIds(List<SearchCriterion> params);
}
