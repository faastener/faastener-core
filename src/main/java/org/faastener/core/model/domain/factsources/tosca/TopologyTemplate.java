package org.faastener.core.model.domain.factsources.tosca;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopologyTemplate {
    private List<NodeTemplate> nodeTemplates;
    private List<RelationshipTemplate> relationshipTemplates;
}
