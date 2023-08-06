package org.faastener.core.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.faastener.core.model.entities.search.SearchCriterion;
import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.faastener.core.model.entities.search.TechnologyDossierQueryBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TechnologyDossierSearchImpl implements TechnologyDossierSearch {
    private final MongoTemplate mongoTemplate;

    public TechnologyDossierSearchImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<TechnologyDossierEntity> searchDossiers(List<SearchCriterion> params) {
        return findDossiers(params, false);
    }

    @Override
    public List<String> searchDossierIds(List<SearchCriterion> params) {
        return findDossiers(params, true)
                .stream()
                .map(TechnologyDossierEntity::getId)
                .collect(Collectors.toList());
    }

    private List<TechnologyDossierEntity> findDossiers(List<SearchCriterion> params, boolean onlyIds) {
        TechnologyDossierQueryBuilder queryBuilder = new TechnologyDossierQueryBuilder();
        params.forEach(queryBuilder);
        Query filterQuery = queryBuilder.getQuery();

        if (onlyIds) {
            filterQuery.fields().include("_id");
        }

        if (params.isEmpty()) {
            log.debug("Provided search string was empty, returning an empty TechnologyDossierEntity List");
            return new ArrayList<>();
        }

        return mongoTemplate.find(filterQuery, TechnologyDossierEntity.class);
    }
}
