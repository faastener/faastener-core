package org.faastener.core.repositories;

import java.util.List;

import org.faastener.core.model.SearchCriterion;
import org.faastener.core.model.TechnologyDossier;

public interface TechnologyDossierSearch {
    List<TechnologyDossier> searchDossiers(List<SearchCriterion> params);

    List<String> searchDossierIds(List<SearchCriterion> params);
}
