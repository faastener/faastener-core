package org.faastener.core.model.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.TechnologyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "frameworks")
public class ClassificationFrameworkEntity {
    @Id
    public String id;
    public String name;
    public TechnologyType technologyType;
    public String version;
    public String description;
    public List<FrameworkViewEntity> frameworkViews;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FrameworkViewEntity {
        public String id;
        public String name;
        public String description;
        public List<CriteriaGroupingEntity> criteriaGroupings;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriteriaGroupingEntity {
        public String id;
        public String name;
        public String locator;
        public List<CriteriaGroupingEntity> criteriaGroupings;
        public List<CriterionTypeEntity> criteria;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriterionTypeEntity {
        public String id;
        public String name;
        public String description;
        public List<String> exampleValues;
    }
}
