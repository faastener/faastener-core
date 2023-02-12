package org.faastener.core.services;

import java.util.List;
import java.util.Optional;

import org.faastener.core.model.ClassificationFramework;

public interface ClassificationFrameworkService {
    /**
     * Returns the classification framework with the specified ID.
     *
     * @param id The ID of the classification framework to return.
     * @return The classification framework with the specified ID.
     */
    Optional<ClassificationFramework> findById(String id);

    /**
     * Returns the classification framework with the specified product ID.
     *
     * @param productId The product ID for which to return the classification framework.
     * @return The classification framework for the specified product ID.
     */
    Optional<ClassificationFramework> findByProductId(Integer productId);

    /**
     * Returns all classification frameworks in the database.
     *
     * @return All classification frameworks in the database.
     */
    List<ClassificationFramework> findAll();

    /**
     * Saves the specified classification framework to the database.
     *
     * @param classificationFramework The classification framework to save.
     * @return The saved classification framework, including a newly generated ID.
     */
    ClassificationFramework save(ClassificationFramework classificationFramework);

    /**
     * Updates the specified classification framework in the database.
     *
     * @param classificationFramework The classification framework to update.
     * @return The updated classification framework.
     */
    ClassificationFramework update(ClassificationFramework classificationFramework);

    /**
     * Deletes the classification framework with the specified ID.
     *
     * @param id The ID of the classification framework to delete.
     */
    void delete(String id);
}
