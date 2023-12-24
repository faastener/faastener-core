package org.faastener.core.factextraction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToscaFact {
    private String elementId;
    private String factType;
    private String factValue;
}
