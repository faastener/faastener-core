package org.faastener.core.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.TechnologyType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {
    public String id;
    public String technologyName;
    public TechnologyType technologyType;
    public String logoLocation;
    public String shortDescription;
    public String description;
    public TechnologyDossier dossier;
}
