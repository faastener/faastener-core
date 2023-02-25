package org.faastener.core.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FilterType {
    CONTAINS_ONE("containsOne"),
    CONTAINS_ANY("containsAny"),
    LTE("lte"),
    GTE("gte"),
    BOOL("bool");

    public final String filterType;

    private FilterType(String filterType) {
        this.filterType = filterType;
    }

    @JsonCreator
    public static FilterType fromString(String filterType) {
        if (Objects.isNull(filterType)) return null;
        if ("containsOne".equalsIgnoreCase(filterType)) {
            return FilterType.CONTAINS_ONE;
        } else if ("containsAny".equalsIgnoreCase(filterType)) {
            return FilterType.CONTAINS_ANY;
        } else if ("lte".equalsIgnoreCase(filterType)) {
            return FilterType.LTE;
        } else if ("gte".equalsIgnoreCase(filterType)) {
            return FilterType.GTE;
        } else
            return FilterType.BOOL;
    }

    @JsonValue
    public String getFilterType() {
        return filterType;
    }
}
