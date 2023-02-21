package org.faastener.core.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.faastener.core.model.TechnologyDossier;
import org.faastener.core.services.TechnologyDossierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dossiers")
public class TechnologyDossierController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyDossierController.class);
    private final TechnologyDossierService service;

    public TechnologyDossierController(TechnologyDossierService service) {
        this.service = service;
    }

    /**
     * Returns all available technology dossiers.
     *
     * @return The list of technology dossiers.
     */
    @GetMapping
    public List<TechnologyDossier> getDossiers() {
        return service.findAll();
    }

    /**
     * Returns the technology dossiers with the specified ID.
     *
     * @param dossierId The ID of the technology dossiers to return.
     * @return The technology dossiers with the specified ID, or 404 Not Found.
     */
    @GetMapping
    @RequestMapping("{dossierId}")
    public ResponseEntity<?> getDossier(@PathVariable String dossierId) {
        return service.findById(dossierId)
                .map(dossier -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .location(new URI("/api/v1/dossiers/" + dossier.getId()))
                                .body(dossier);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
