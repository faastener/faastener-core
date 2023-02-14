package org.faastener.core.model;

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "frameworks")
public class ClassificationFramework {
    @Id
    public String id;
    @Field("name")
    public String name;
    @Field("technologyType")
    public TechnologyType technologyType;
    @Field("version")
    public String version;
    @Field("description")
    public String description;
    @Field("frameworkViews")
    public List<FrameworkView> frameworkViews;

    public ClassificationFramework() {
    }

    public ClassificationFramework(String id, String name, TechnologyType technologyType, String version, String description, List<FrameworkView> frameworkViews) {
        this.id = id;
        this.name = name;
        this.technologyType = technologyType;
        this.version = version;
        this.description = description;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassificationFramework that = (ClassificationFramework) o;
        return id.equals(that.id) && name.equals(that.name) && technologyType == that.technologyType && version.equals(that.version) && Objects.equals(description, that.description) && Objects.equals(frameworkViews, that.frameworkViews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, technologyType, version, description, frameworkViews);
    }

    @Override
    public String toString() {
        return "ClassificationFramework{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", technologyType=" + technologyType +
                ", version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", frameworkViews=" + frameworkViews +
                '}';
    }
}
