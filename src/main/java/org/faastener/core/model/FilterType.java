package org.faastener.core.model;

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
}
