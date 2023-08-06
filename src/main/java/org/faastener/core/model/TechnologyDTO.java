package org.faastener.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.faastener.core.model.common.TechnologyType;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TechnologyDTO {
    public String id;
    public String technologyName;
    public TechnologyType technologyType;
    public String logoLocation;
    public String shortDescription;
    public String description;
    public TechnologyDossierEntity dossier;
}
