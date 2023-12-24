package org.faastener.core.factextraction;

import java.util.Optional;

import org.faastener.core.model.domain.factsources.tosca.TopologyTemplate;

@FunctionalInterface
public interface ToscaFactExtractor {
    Optional<ToscaFact> extractForElement(String elementId, TopologyTemplate topologyTemplate);
}
