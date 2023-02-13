package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

public class CriteriaGrouping {
    public String id;
    public String name;
    public String locator;
    public List<CriteriaGrouping> criteriaGroupings;
    public List<CriterionType> criteria;

    public CriteriaGrouping() {
    }

    public CriteriaGrouping(String id, String name, String locator, List<CriteriaGrouping> criteriaGroupings, List<CriterionType> criteria) {
        this.id = id;
        this.name = name;
        this.locator = locator;
        this.criteriaGroupings = criteriaGroupings;
        this.criteria = criteria;
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

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public List<CriteriaGrouping> getCriteriaGroupings() {
        return criteriaGroupings;
    }

    public void setCriteriaGroupings(List<CriteriaGrouping> criteriaGroupings) {
        this.criteriaGroupings = criteriaGroupings;
    }

    public List<CriterionType> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<CriterionType> criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriteriaGrouping that = (CriteriaGrouping) o;
        return id.equals(that.id) && name.equals(that.name) && Objects.equals(locator, that.locator) && Objects.equals(criteriaGroupings, that.criteriaGroupings) && Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, locator, criteriaGroupings, criteria);
    }

    @Override
    public String toString() {
        return "CriteriaGrouping{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", locator='" + locator + '\'' +
                ", criteriaGroupings=" + criteriaGroupings +
                ", criteria=" + criteria +
                '}';
    }
}
