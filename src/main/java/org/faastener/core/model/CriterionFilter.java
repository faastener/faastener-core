package org.faastener.core.model;

import java.util.Objects;

public class CriterionFilter {
    public String criterionTypeId;
    public String displayName;
    public FilterType filterType;

    public CriterionFilter() {
    }

    public CriterionFilter(String criterionTypeId, String displayName, FilterType filterType) {
        this.criterionTypeId = criterionTypeId;
        this.displayName = displayName;
        this.filterType = filterType;
    }

    public String getCriterionTypeId() {
        return criterionTypeId;
    }

    public void setCriterionTypeId(String criterionTypeId) {
        this.criterionTypeId = criterionTypeId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriterionFilter that = (CriterionFilter) o;
        return criterionTypeId.equals(that.criterionTypeId) && displayName.equals(that.displayName) && filterType == that.filterType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(criterionTypeId, displayName, filterType);
    }

    @Override
    public String toString() {
        return "CriterionFilter{" +
                "typeId='" + criterionTypeId + '\'' +
                ", displayName='" + displayName + '\'' +
                ", filterType=" + filterType +
                '}';
    }
}
