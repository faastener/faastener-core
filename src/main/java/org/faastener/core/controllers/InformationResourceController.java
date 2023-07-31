package org.faastener.core.controllers;

import org.faastener.core.model.InformationSection;
import org.faastener.core.services.InformationResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/infosections")
public class InformationResourceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InformationResourceController.class);

    private final InformationResourceService informationResourceService;

    public InformationResourceController(InformationResourceService informationResourceService) {
        this.informationResourceService = informationResourceService;
    }

    /**
     * Returns all available technologies.
     *
     * @return The list of technologies.
     */
    @GetMapping
    public Iterable<InformationSection> getInformationSections() {
        return this.informationResourceService.findAll();
    }
}
