package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.TechnologyDossier;

public interface TechnologyDossierService {
    /**
     * Returns the technology dossier with the specified ID.
     *
     * @param id The ID of the technology dossier to return.
     * @return The technology dossier with the specified ID.
     */
    Optional<TechnologyDossier> findById(String id);

    /**
     * Returns all technology dossiers in the database.
     *
     * @return All technology dossiers in the database.
     */
    List<TechnologyDossier> findAll();

    /**
     * Saves the specified technology dossier to the database.
     *
     * @param technologyDossier The technology dossier to save.
     * @return The saved technology dossier, including a newly generated ID.
     */
    TechnologyDossier save(TechnologyDossier technologyDossier);

    /**
     * Updates the specified technology dossier in the database.
     *
     * @param technologyDossier The technology dossier to update.
     * @return The updated technology dossier.
     */
    TechnologyDossier update(TechnologyDossier technologyDossier);

    /**
     * Deletes the technology dossier with the specified ID.
     *
     * @param id The ID of the technology dossier to delete.
     */
    void delete(String id);
}
