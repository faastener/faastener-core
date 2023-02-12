package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "frameworks")
public class ClassificationFramework {
    @Id
    public String id;
    public String name;
    public String description;
    public TechnologyType technologyType;
    public List<FrameworkView> frameworkViews;

    public ClassificationFramework(String id, String name, String description, TechnologyType technologyType, List<FrameworkView> frameworkViews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.technologyType = technologyType;
        this.frameworkViews = frameworkViews;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TechnologyType getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(TechnologyType technologyType) {
        this.technologyType = technologyType;
    }

    public List<FrameworkView> getFrameworkViews() {
        return frameworkViews;
    }

    public void setFrameworkViews(List<FrameworkView> frameworkViews) {
        this.frameworkViews = frameworkViews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificationFramework that = (ClassificationFramework) o;
        return id.equals(that.id) && name.equals(that.name) && description.equals(that.description) && technologyType == that.technologyType && frameworkViews.equals(that.frameworkViews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, technologyType, frameworkViews);
    }
}
