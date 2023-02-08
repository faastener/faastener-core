package org.faastener.core.models;

import java.util.List;

public class CriteriaGrouping {
    public String id;
    public String name;
    public String locator;
    public List<CriteriaGrouping> criteriaGroupings;
    public List<CriterionType> classificationCriteria;
}
