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
@Document(collection = "infosections")
public class InformationSectionEntity {
    @Id
    public String id;
    public String title;
    public String description;
    public TechnologyType technologyType;
    public List<InformationResourceEntity> resources;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InformationResourceEntity {
        public String id;
        public String resource;
        public String reference;
    }
}


