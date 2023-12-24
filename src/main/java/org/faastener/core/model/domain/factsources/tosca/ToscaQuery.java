package org.faastener.core.model.domain.factsources.tosca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToscaQuery {
    private String selectedNode;
    private TopologyTemplate topology;
}
