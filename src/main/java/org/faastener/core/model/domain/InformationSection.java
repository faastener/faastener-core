package org.faastener.core.model.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.TechnologyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationSection {
    public String id;
    public String title;
    public String description;
    public TechnologyType technologyType;
    public List<InformationResource> resources;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InformationResource {
        public String id;
        public String resource;
        public String reference;
    }
}


