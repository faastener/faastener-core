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
