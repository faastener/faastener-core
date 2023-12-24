package org.faastener.core.model.domain.factsources.tosca;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Requirement {
    private String id;
    private String name;
    private String node;
    private String relationship;
}
