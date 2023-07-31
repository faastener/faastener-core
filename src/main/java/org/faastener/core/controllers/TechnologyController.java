package org.faastener.core.controllers;

import java.net.URI;
import java.net.URISyntaxException;

import org.faastener.core.model.Technology;
import org.faastener.core.services.TechnologyDossierService;
import org.faastener.core.services.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/technologies")
public class TechnologyController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyController.class);

    private final TechnologyService technologyService;
    private final TechnologyDossierService technologyDossierService;

    public TechnologyController(TechnologyService technologyService, TechnologyDossierService technologyDossierService) {
        this.technologyService = technologyService;
        this.technologyDossierService = technologyDossierService;
    }

    /**
     * Returns all available technologies.
     *
     * @return The list of technologies.
     */
    @GetMapping
    public Iterable<Technology> getTechnologies(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            return null;
            //return this.technologyService.findAll(search);
        } else {
            return this.technologyService.findAll();
        }
    }

    /**
     * Returns the technology with the specified ID.
     *
     * @param technologyId The ID of the technology to return.
     * @return The technology with the specified ID, or 404 Not Found.
     */
    @GetMapping
    @RequestMapping("{technologyId}")
    public ResponseEntity<?> getTechnology(@PathVariable String technologyId) {
        return technologyService.findById(technologyId)
                .map(technology -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .location(new URI("/api/technologies/" + technology.getId()))
                                .body(technology);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
