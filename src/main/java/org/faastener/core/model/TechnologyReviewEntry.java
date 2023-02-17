package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

public class TechnologyReviewEntry {
    public String typeId;
    public List<CriterionValue> values;

    public TechnologyReviewEntry() {
    }

    public TechnologyReviewEntry(String typeId, List<CriterionValue> values) {
        this.typeId = typeId;
        this.values = values;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List<CriterionValue> getValues() {
        return values;
    }

    public void setValues(List<CriterionValue> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnologyReviewEntry that = (TechnologyReviewEntry) o;
        return typeId.equals(that.typeId) && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, values);
    }

    @Override
    public String toString() {
        return "TechnologyReviewEntry{" +
                "typeId='" + typeId + '\'' +
                ", values=" + values +
                '}';
    }
}
