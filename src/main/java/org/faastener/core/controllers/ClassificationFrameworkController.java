package org.faastener.core.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.services.ClassificationFrameworkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/frameworks")
public class ClassificationFrameworkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassificationFrameworkController.class);
    private final ClassificationFrameworkService service;

    public ClassificationFrameworkController(ClassificationFrameworkService service) {
        this.service = service;
    }

    /**
     * Returns all available classification frameworks.
     *
     * @return The list of classification frameworks.
     */
    @GetMapping
    public List<ClassificationFramework> getFrameworks() {
        return service.findAll();
    }

    /**
     * Returns the classification framework with the specified ID.
     *
     * @param frameworkId The ID of the classification framework to return.
     * @return The classification framework with the specified ID, or 404 Not Found.
     */
    @GetMapping
    @RequestMapping("{frameworkId}")
    public ResponseEntity<?> getFramework(@PathVariable String frameworkId) {
        return service.findById(frameworkId)
                .map(framework -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(Integer.toString(42))
                                .location(new URI("/api/v1/frameworks/" + framework.getId()))
                                .body(framework);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
