package org.faastener.core.services;

import org.faastener.core.model.domain.factsources.tosca.ToscaQuery;

public interface QueryGenerationService {
    String generateSearchQuery(ToscaQuery factSource);
}
