package org.faastener.core.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.faastener.core.model.domain.factsources.tosca.ToscaQuery;
import org.faastener.core.services.QueryGenerationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search-queries")
public class SearchQueryController {

    private final QueryGenerationService queryGenerationService;

    public SearchQueryController(QueryGenerationService queryGenerationService) {
        this.queryGenerationService = queryGenerationService;
    }

    @PostMapping
    public ResponseEntity<String> generateQuery(@RequestBody ToscaQuery factSource) {
        System.out.println(factSource);

        String generated = queryGenerationService.generateSearchQuery(factSource);
        System.out.println(generated);

        URI uri;
        try {
            String request = null;
            if (!generated.isEmpty()) {
                uri = new URI(
                        "http",
                        "localhost:8082",
                        "/api/technologies",
                        "search=" + generated,
                        null);

                request = uri.toASCIIString();
            } else {
                request = "http://localhost:8082/api/technologies";
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{ \"url\": \"" + request + "\"}");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }
}
