package org.faastener.core.model.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.FilterType;
import org.faastener.core.model.common.TechnologyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterConfiguration {
    public String id;
    public TechnologyType technologyType;
    public String name;
    public List<CriterionFilter> filters;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CriterionFilter {
        public String criterionTypeId;
        public String displayName;
        public FilterType filterType;
    }
}


