package org.faastener.core.model.domain.factsources.tosca;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NodeTemplate {
    private String id;
    private String type;
    private String name;
    private Map<String, String> properties;
    private List<Capability> capabilities;
    private List<Requirement> requirements;
}
