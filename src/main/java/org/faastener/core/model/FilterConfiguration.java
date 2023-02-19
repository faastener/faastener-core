package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "filters")
public class FilterConfiguration {
    @Id
    public String id;
    public TechnologyType technologyType;
    public String name;
    public List<CriterionFilter> filters;

    public FilterConfiguration() {
    }

    public FilterConfiguration(String id, TechnologyType technologyType, String name, List<CriterionFilter> filters) {
        this.id = id;
        this.technologyType = technologyType;
        this.name = name;
        this.filters = filters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TechnologyType getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(TechnologyType technologyType) {
        this.technologyType = technologyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CriterionFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<CriterionFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterConfiguration that = (FilterConfiguration) o;
        return id.equals(that.id) && technologyType == that.technologyType && name.equals(that.name) && Objects.equals(filters, that.filters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, technologyType, name, filters);
    }

    @Override
    public String toString() {
        return "FilterConfiguration{" +
                "id='" + id + '\'' +
                ", technologyType=" + technologyType +
                ", name='" + name + '\'' +
                ", filters=" + filters +
                '}';
    }
}
