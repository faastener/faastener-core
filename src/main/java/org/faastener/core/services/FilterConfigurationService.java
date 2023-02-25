package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.FilterConfiguration;

public interface FilterConfigurationService {
    /**
     * Returns the filter configuration with the specified ID.
     *
     * @param id The ID of the filter configuration to return.
     * @return The filter configuration with the specified ID.
     */
    Optional<FilterConfiguration> findById(String id);

    /**
     * Returns all filter configurations in the database.
     *
     * @return All filter configurations in the database.
     */
    List<FilterConfiguration> findAll();

    /**
     * Saves the specified filter configuration to the database.
     *
     * @param filterConfiguration The filter configuration to save.
     * @return The saved filter configuration, including a newly generated ID.
     */
    FilterConfiguration save(FilterConfiguration filterConfiguration);

    /**
     * Updates the specified filter configuration in the database.
     *
     * @param filterConfiguration The filter configuration to update.
     * @return The updated filter configuration.
     */
    FilterConfiguration update(FilterConfiguration filterConfiguration);

    /**
     * Deletes the filter configuration with the specified ID.
     *
     * @param id The ID of the filter configuration to delete.
     */
    void delete(String id);
}
