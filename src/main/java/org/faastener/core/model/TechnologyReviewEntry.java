package org.faastener.core.model;

import java.util.Objects;

public class TechnologyReviewEntry {
    public String id;
    public String typeId;
    public CriterionValue value;

    public TechnologyReviewEntry() {
    }

    public TechnologyReviewEntry(String id, String typeId, CriterionValue value) {
        this.id = id;
        this.typeId = typeId;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public CriterionValue getValue() {
        return value;
    }

    public void setValue(CriterionValue value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnologyReviewEntry that = (TechnologyReviewEntry) o;
        return id.equals(that.id) && typeId.equals(that.typeId) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeId, value);
    }

    @Override
    public String toString() {
        return "TechnologyReviewEntry{" +
                "id='" + id + '\'' +
                ", typeId='" + typeId + '\'' +
                ", value=" + value +
                '}';
    }
}
