package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.domain.Technology;

public interface TechnologyService {
    /**
     * Returns the technology with the specified ID.
     *
     * @param id The ID of the technology to return.
     * @return The technology with the specified ID.
     */
    Optional<Technology> findTechnologyById(String id, boolean dossierRequested);

    /**
     * Returns all technologies in the database.
     *
     * @return All technologies in the database.
     */
    List<Technology> findTechnologies(boolean dossierRequested);

    /**
     * Returns all technologies in the database that match the provided filter string.
     *
     * @param filter The filter string.
     * @return All technologies in the database matching the filter string.
     */
    List<Technology> findTechnologies(String filter, boolean dossierRequested);

    /**
     * Saves the specified technology to the database.
     *
     * @param technology The technology to save.
     * @return The saved technology, including a newly generated ID.
     */
    Technology save(Technology technology);

    /**
     * Updates the specified technology in the database.
     *
     * @param technology The technology to update.
     * @return The updated technology.
     */
    Technology update(Technology technology);

    /**
     * Deletes the technology with the specified ID.
     *
     * @param id The ID of the technology to delete.
     */
    void delete(String id);
}
