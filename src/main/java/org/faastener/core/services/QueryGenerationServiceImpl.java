package org.faastener.core.services;

import org.faastener.core.factextraction.FactExtractionProcessor;
import org.faastener.core.model.domain.factsources.tosca.ToscaQuery;
import org.springframework.stereotype.Service;

@Service
public class QueryGenerationServiceImpl implements QueryGenerationService {
    @Override
    public String generateSearchQuery(ToscaQuery factSource) {
        FactExtractionProcessor processor = new FactExtractionProcessor(factSource);
        processor.extractFacts();

        return processor.generateQueryString();
    }
}
