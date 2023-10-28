package org.faastener.core.model.entities.search;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.mongodb.core.query.Criteria;

public class TechnologySearchRsqlVisitor implements RSQLVisitor<Criteria, Void> {

    private final TechnologyCriteriaBuilder builder;

    public TechnologySearchRsqlVisitor() {
        builder = new TechnologyCriteriaBuilder();
    }

    @Override
    public Criteria visit(final AndNode node, final Void param) {
        return builder.createCriteria(node);
    }

    @Override
    public Criteria visit(final OrNode node, final Void param) {
        return builder.createCriteria(node);
    }

    @Override
    public Criteria visit(final ComparisonNode node, final Void params) {
        return builder.createCriteria(node);
    }
}
