package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.InformationSection;

public interface InformationResourceService {
    /**
     * Returns the information section with the specified ID.
     *
     * @param id The ID of the information section to return.
     * @return The information section with the specified ID.
     */
    Optional<InformationSection> findById(String id);

    /**
     * Returns all information section in the database.
     *
     * @return All information section in the database.
     */
    List<InformationSection> findAll();

    /**
     * Saves the specified information section to the database.
     *
     * @param infoSection The information section to save.
     * @return The saved information section, including a newly generated ID.
     */
    InformationSection save(InformationSection infoSection);

    /**
     * Updates the specified information section in the database.
     *
     * @param infoSection The information section to update.
     * @return The updated information section.
     */
    InformationSection update(InformationSection infoSection);

    /**
     * Deletes the information section with the specified ID.
     *
     * @param id The ID of the information section to delete.
     */
    void delete(String id);
}
