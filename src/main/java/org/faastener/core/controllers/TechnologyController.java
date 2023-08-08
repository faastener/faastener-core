package org.faastener.core.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.faastener.core.model.domain.Technology;
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

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    /**
     * Returns all available technologies.
     *
     * @param search      A search query containing key-value pairs for matching technologies based on available reviewed criteria.
     * @param withDossier Boolean flag for including available reviewed criteria in the response.
     * @return The list of technologies.
     */
    @GetMapping
    public List<Technology> getTechnologies(@RequestParam(value = "search", required = false) String search, @RequestParam(defaultValue = "false", required = false) String withDossier) {
        boolean dossierRequested = Boolean.parseBoolean(withDossier);
        List<Technology> res;

        if (search != null) {
            res = this.technologyService.findTechnologies(search, dossierRequested);
        } else {
            res = this.technologyService.findTechnologies(dossierRequested);
        }

        return res;
    }

    /**
     * Returns the technology with the specified ID.
     *
     * @param technologyId The ID of the technology to return.
     * @return The technology with the specified ID, or 404 Not Found.
     */
    @GetMapping
    @RequestMapping("{technologyId}")
    public ResponseEntity<?> getTechnology(@PathVariable String technologyId, @RequestParam(defaultValue = "false", required = false) String withDossier) {
        boolean dossierRequested = Boolean.parseBoolean(withDossier);

        return technologyService.findTechnologyById(technologyId, dossierRequested)
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
