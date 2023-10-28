package org.faastener.core.repositories;

import java.util.List;
import java.util.stream.Collectors;

import cz.jirutka.rsql.parser.ast.Node;
import lombok.extern.slf4j.Slf4j;
import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.faastener.core.model.entities.search.TechnologySearchRsqlVisitor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
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
    public List<TechnologyDossierEntity> searchDossiers(Node rootQuery) {
        return findDossiers(rootQuery, false);
    }

    @Override
    public List<String> searchDossierIds(Node rootQuery) {
        return findDossiers(rootQuery, true)
                .stream()
                .map(TechnologyDossierEntity::getId)
                .collect(Collectors.toList());
    }

    private List<TechnologyDossierEntity> findDossiers(Node rootQuery, boolean onlyIds) {
        Criteria searchCriteria = rootQuery.accept(new TechnologySearchRsqlVisitor());
        Query filterQuery = new Query(searchCriteria);

        if (onlyIds) {
            filterQuery.fields().include("_id");
        }

        /*if (params.isEmpty()) {
            log.debug("Provided search string was empty, returning an empty TechnologyDossierEntity List");
            return new ArrayList<>();
        }*/

        return mongoTemplate.find(filterQuery, TechnologyDossierEntity.class);
    }
}
