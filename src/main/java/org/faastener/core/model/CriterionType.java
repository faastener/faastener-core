package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

public class CriterionType {
    public String id;
    public String name;
    public String description;
    public List<String> exampleValues;

    public CriterionType() {
    }

    public CriterionType(String id, String name, String description, List<String> exampleValues) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exampleValues = exampleValues;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getExampleValues() {
        return exampleValues;
    }

    public void setExampleValues(List<String> exampleValues) {
        this.exampleValues = exampleValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriterionType that = (CriterionType) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(description, that.description) && Objects.equals(exampleValues, that.exampleValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, exampleValues);
    }

    @Override
    public String toString() {
        return "CriterionType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exampleValues=" + exampleValues +
                '}';
    }
}
