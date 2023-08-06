package org.faastener.core.model.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.FilterType;
import org.faastener.core.model.common.TechnologyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "filters")
public class FilterConfigurationEntity {
    @Id
    public String id;
    public TechnologyType technologyType;
    public String name;
    public List<CriterionFilterEntity> filters;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriterionFilterEntity {
        public String criterionTypeId;
        public String displayName;
        public FilterType filterType;
    }
}


