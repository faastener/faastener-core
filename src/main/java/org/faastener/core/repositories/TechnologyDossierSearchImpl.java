package org.faastener.core.repositories;

import java.util.List;
import java.util.stream.Collectors;

import org.faastener.core.model.SearchCriterion;
import org.faastener.core.model.TechnologyDossier;
import org.faastener.core.model.TechnologyDossierQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class TechnologyDossierSearchImpl implements TechnologyDossierSearch {
    private final MongoTemplate mongoTemplate;

    public TechnologyDossierSearchImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<TechnologyDossier> searchDossiers(List<SearchCriterion> params) {
        return findDossiers(params, false);
    }

    @Override
    public List<String> searchDossierIds(List<SearchCriterion> params) {
        return findDossiers(params, true)
                .stream()
                .map(TechnologyDossier::getId)
                .collect(Collectors.toList());
    }

    private List<TechnologyDossier> findDossiers(List<SearchCriterion> params, boolean onlyIds) {
        TechnologyDossierQueryBuilder queryBuilder = new TechnologyDossierQueryBuilder();
        params.forEach(queryBuilder);
        Query filterQuery = queryBuilder.getQuery();

        if (onlyIds) {
            filterQuery.fields().include("_id");
        }

        return mongoTemplate.find(filterQuery, TechnologyDossier.class);
    }
}
