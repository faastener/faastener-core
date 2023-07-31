package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.Technology;
import org.faastener.core.model.TechnologyDossier;

public interface TechnologyService {
    /**
     * Returns the technology with the specified ID.
     *
     * @param id The ID of the technology to return.
     * @return The technology with the specified ID.
     */
    Optional<Technology> findById(String id);

    /**
     * Returns all technologies in the database.
     *
     * @return All technologies in the database.
     */
    List<Technology> findAll();

    /**
     * Returns all technologies in the database that match the provided filter string.
     *
     * @param filter The filter string.
     * @return All technologies in the database matching the filter string.
     */
    List<TechnologyDossier> findAll(String filter);

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
