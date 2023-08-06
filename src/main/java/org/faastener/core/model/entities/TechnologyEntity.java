package org.faastener.core.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.TechnologyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "technologies")
public class TechnologyEntity {
    @Id
    public String id;
    public String technologyName;
    public TechnologyType technologyType;
    public String logoLocation;
    public String shortDescription;
    public String description;
}
