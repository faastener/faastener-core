package org.faastener.core.model.entities.search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class TechnologyDossierQueryBuilder implements Consumer<SearchCriterion> {
    private static final String PREFIX = "reviewedCriteria.";
    private static final String SUFFIX = ".value";
    private Query query;
    private final List<Criteria> criteria;

    public TechnologyDossierQueryBuilder() {
        super();
        this.criteria = new ArrayList<>();
    }

    @Override
    public void accept(SearchCriterion searchCriterion) {
        // Criteria.where("metadata.name").is(doc.getName()).and("metadata.version").is(doc.getVersion())
        Criteria expression = new Criteria();
        String keyName = PREFIX + searchCriterion.getKey() + SUFFIX;

        if (searchCriterion.getOperation().equalsIgnoreCase(">")) {
            expression.and(keyName).is(searchCriterion.getValue());
        } else if (searchCriterion.getOperation().equalsIgnoreCase("<")) {
            expression.and(keyName).is(searchCriterion.getValue());
        } else if (searchCriterion.getOperation().equalsIgnoreCase(":")) {
            expression.and(keyName).is(searchCriterion.getValue());
        }
        criteria.add(expression);
    }

    public Query getQuery() {
        Criteria allCriteria = new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()]));

        return new Query(allCriteria);
    }
}
