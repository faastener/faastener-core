package org.faastener.core.model.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.TechnologyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationFramework {
    public String id;
    public String name;
    public TechnologyType technologyType;
    public String version;
    public String description;
    public List<FrameworkView> frameworkViews;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FrameworkView {
        public String id;
        public String name;
        public String description;
        public List<CriteriaGrouping> criteriaGroupings;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriteriaGrouping {
        public String id;
        public String name;
        public String locator;
        public List<CriteriaGrouping> criteriaGroupings;
        public List<CriterionType> criteria;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriterionType {
        public String id;
        public String name;
        public String description;
        public List<String> exampleValues;
    }
}
