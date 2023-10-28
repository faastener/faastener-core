package org.faastener.core.model.domain.factsources.tosca;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TTopologyTemplate {
    private List<TNodeTemplate> nodeTemplates;
    private List<TRelationshipTemplate> relationshipTemplates;
}
