package org.faastener.core.model.domain.factsources.tosca;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipTemplate {
    private String id;
    private String name;
    private String type;
    private Map<String, String> properties;
    private String sourceElementRef;
    private String targetElementRef;
}
