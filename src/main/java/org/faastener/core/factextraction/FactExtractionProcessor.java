package org.faastener.core.factextraction;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.faastener.core.model.common.FilterType;
import org.faastener.core.model.domain.factsources.tosca.ToscaQuery;

@Data
@NoArgsConstructor
public class FactExtractionProcessor {
    private ToscaQuery factSource;
    private List<ToscaFact> extractedFacts;
    private Map<String, ToscaFactMapping> factMappings = Map.of(
            "faas.runtime", new ToscaFactMapping("faas.runtime", "funcRuntimes", FilterType.CONTAINS_ANY),
            "faas.blobstorage", new ToscaFactMapping("faas.blobstorage", "datastoreBlob", FilterType.CONTAINS_ANY)
    );

    public FactExtractionProcessor(ToscaQuery factSource) {
        this.factSource = factSource;
    }

    public void extractFacts() {
        ToscaFactsExtractor extractor = new ToscaFactsExtractor();
        extractedFacts = extractor.extractFactsForElement(factSource.getSelectedNode(), factSource.getTopology());
    }

    public String generateQueryString() {
        StringBuilder sb = new StringBuilder();

        for (ToscaFact fact : extractedFacts) {
            if (factMappings.containsKey(fact.getFactType())) {
                ToscaFactMapping mapping = factMappings.get(fact.getFactType());

                sb.append(mapping.getCriterionId());
                if (mapping.getFilterType() == FilterType.CONTAINS_ANY) {
                    sb.append("=in=(");
                    if ("true".equals(fact.getFactValue())) {
                        sb.append("*");
                    } else {
                        sb.append(fact.getFactValue());
                    }
                    sb.append(")");
                    sb.append(";");
                }
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }
}
