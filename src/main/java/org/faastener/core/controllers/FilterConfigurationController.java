package org.faastener.core.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.faastener.core.model.FilterConfiguration;
import org.faastener.core.services.FilterConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/filters")
public class FilterConfigurationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FilterConfigurationController.class);
    private final FilterConfigurationService service;

    public FilterConfigurationController(FilterConfigurationService service) {
        this.service = service;
    }

    /**
     * Returns all available filter configurations.
     *
     * @return The list of filter configurations.
     */
    @GetMapping
    public List<FilterConfiguration> getFilterConfigurations() {
        return service.findAll();
    }

    /**
     * Returns the filter configurations with the specified ID.
     *
     * @param filterConfigurationId The ID of the filter configurations to return.
     * @return The filter configurations with the specified ID, or 404 Not Found.
     */
    @GetMapping
    @RequestMapping("{filterConfigurationId}")
    public ResponseEntity<?> getFilterConfiguration(@PathVariable String filterConfigurationId) {
        return service.findById(filterConfigurationId)
                .map(filterConfiguration -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .location(new URI("/api/filters/" + filterConfiguration.getId()))
                                .body(filterConfiguration);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
