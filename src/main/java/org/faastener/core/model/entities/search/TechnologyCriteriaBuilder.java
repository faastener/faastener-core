package org.faastener.core.model.entities.search;

import java.util.List;
import java.util.Objects;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;

@NoArgsConstructor
public class TechnologyCriteriaBuilder {
    private static final String PREFIX = "reviewedCriteria.";
    private static final String SUFFIX = ".value";

    public Criteria createCriteria(final Node node) {
        if (node instanceof LogicalNode) {
            return createCriteria((LogicalNode) node);
        }
        if (node instanceof ComparisonNode) {
            return createCriteria((ComparisonNode) node);
        }
        return null;
    }

    public Criteria createCriteria(final LogicalNode logicalNode) {

        List<Criteria> specs = logicalNode.getChildren()
                .stream()
                .map(this::createCriteria)
                .filter(Objects::nonNull)
                .toList();

        Criteria result = new Criteria();
        if (logicalNode.getOperator() == LogicalOperator.AND) {
            result = result.andOperator(specs);
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            result = result.orOperator(specs);
        }

        return result;
    }

    public Criteria createCriteria(final ComparisonNode comparisonNode) {
        return toCriteria(comparisonNode.getSelector(), comparisonNode.getOperator(), comparisonNode.getArguments());
    }

    public Criteria toCriteria(final String property, final ComparisonOperator operator, final List<String> arguments) {
        final String keyName = buildKeyName(property);

        switch (RsqlSearchOperation.getSimpleOperator(operator)) {

            case EQUAL: {
                if (arguments != null && arguments.size() > 1) {
                    return Criteria.where(keyName).is(arguments.get(0));
                } else if (arguments == null || arguments.isEmpty()) {
                    return Criteria.where(keyName).isNull();
                } else {
                    return Criteria.where(keyName).is(arguments.get(0));
                }
            }

            /*case NOT_EQUAL: {
                if (argument instanceof String) {
                    return builder.notLike(root.<String>get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNotNull(root.get(property));
                } else {
                    return builder.notEqual(root.get(property), argument);
                }
            }
            case GREATER_THAN: {
                return Criteria.where(keyName).gt(arguments.get(0));
            }
            case GREATER_THAN_OR_EQUAL: {
                return builder.greaterThanOrEqualTo(root.<String>get(property), argument.toString());
            }
            case LESS_THAN: {
                return builder.lessThan(root.<String>get(property), argument.toString());
            }
            case LESS_THAN_OR_EQUAL: {
                return builder.lessThanOrEqualTo(root.<String>get(property), argument.toString());
            }*/

            case IN:
                return Criteria.where(keyName).in(arguments);
            case NOT_IN:
                return Criteria.where(keyName).nin(arguments);
        }

        return null;
    }

    private String buildKeyName(String keyName) {
        return PREFIX + keyName + SUFFIX;
    }
}
