package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

public class FrameworkView {
    public String id;
    public String name;
    public String description;
    public List<CriteriaGrouping> criteriaGroupings;

    public FrameworkView() {
    }

    public FrameworkView(String id, String name, String description, List<CriteriaGrouping> criteriaGroupings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.criteriaGroupings = criteriaGroupings;
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

    public List<CriteriaGrouping> getCriteriaGroupings() {
        return criteriaGroupings;
    }

    public void setCriteriaGroupings(List<CriteriaGrouping> criteriaGroupings) {
        this.criteriaGroupings = criteriaGroupings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrameworkView that = (FrameworkView) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(description, that.description) && Objects.equals(criteriaGroupings, that.criteriaGroupings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, criteriaGroupings);
    }

    @Override
    public String toString() {
        return "FrameworkView{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", criteriaGroupings=" + criteriaGroupings +
                '}';
    }
}
