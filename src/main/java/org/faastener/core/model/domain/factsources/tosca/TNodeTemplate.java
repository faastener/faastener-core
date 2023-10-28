package org.faastener.core.model.domain.factsources.tosca;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TNodeTemplate {
    public String id;
    public String type;
    public String name;
    public Map<String, String> properties;
}
