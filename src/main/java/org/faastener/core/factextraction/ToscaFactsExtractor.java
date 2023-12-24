package org.faastener.core.factextraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.NoArgsConstructor;
import org.faastener.core.model.domain.factsources.tosca.NodeTemplate;
import org.faastener.core.model.domain.factsources.tosca.RelationshipTemplate;
import org.faastener.core.model.domain.factsources.tosca.TopologyTemplate;

@NoArgsConstructor
public class ToscaFactsExtractor {
    private final Map<String, NodeTemplate> nodesMap = new HashMap<>();
    private final Map<String, RelationshipTemplate> relationshipsMap = new HashMap<>();
    private final List<ToscaFactExtractor> extractors = List.of(
            this::extractFunctionRuntime,
            this::extractObjectStorageTrigger
    );

    public List<ToscaFact> extractFactsForElement(String elementId, TopologyTemplate topologyTemplate) {
        preprocessTopology(topologyTemplate);
        List<ToscaFact> res = new ArrayList<>();

        for (ToscaFactExtractor extractor : extractors) {
            Optional<ToscaFact> extractionResult = extractor.extractForElement(elementId, topologyTemplate);
            extractionResult.ifPresent(res::add);
        }

        return res;
    }

    private Optional<ToscaFact> extractFunctionRuntime(String functionNodeId, TopologyTemplate topologyTemplate) {
        for (NodeTemplate node : topologyTemplate.getNodeTemplates()) {
            if (node.getId().equals(functionNodeId)) {
                Map<String, String> properties = node.getProperties();
                if (properties.containsKey("runtime")) {
                    ToscaFact extracted = new ToscaFact();
                    extracted.setElementId(functionNodeId);
                    extracted.setFactType("faas.runtime");
                    extracted.setFactValue(properties.get("runtime"));

                    return Optional.of(extracted);
                }
            }
        }

        return Optional.empty();
    }

    private Optional<ToscaFact> extractObjectStorageTrigger(String functionNodeId, TopologyTemplate topologyTemplate) {
        for (RelationshipTemplate rel : topologyTemplate.getRelationshipTemplates()) {
            if (rel.getType().equals("{iaas.relationships.abstract}Triggers")) {
                String sourceNodeId = rel.getSourceElementRef();
                String targetNodeId = rel.getTargetElementRef();

                if (targetNodeId.equals(functionNodeId)
                        && nodesMap.containsKey(sourceNodeId)
                        && nodesMap.get(sourceNodeId).getType().equals("{iaas.nodes.abstract}ObjectStorage")
                ) {
                    ToscaFact extracted = new ToscaFact();
                    extracted.setElementId(functionNodeId);
                    extracted.setFactType("faas.blobstorage");
                    extracted.setFactValue("*");

                    return Optional.of(extracted);
                }
            }
        }

        return Optional.empty();
    }

    private void preprocessTopology(TopologyTemplate topologyTemplate) {
        for (NodeTemplate node : topologyTemplate.getNodeTemplates()) {
            nodesMap.put(node.getId(), node);
        }

        for (RelationshipTemplate rel : topologyTemplate.getRelationshipTemplates()) {
            relationshipsMap.put(rel.getId(), rel);
        }
    }
}
