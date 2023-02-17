package org.faastener.core.model;

import java.util.Objects;

public class Technology {
    public String id;
    public String name;
    public TechnologyType technologyType;
    public String description;
    public String shortDescription;
    public String logoLocator;

    public Technology() {
    }

    public Technology(String id, String name, TechnologyType technologyType, String description, String shortDescription, String logoLocator) {
        this.id = id;
        this.name = name;
        this.technologyType = technologyType;
        this.description = description;
        this.shortDescription = shortDescription;
        this.logoLocator = logoLocator;
    }

    public String getId() {
        return id;
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

    public TechnologyType getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(TechnologyType technologyType) {
        this.technologyType = technologyType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLogoLocator() {
        return logoLocator;
    }

    public void setLogoLocator(String logoLocator) {
        this.logoLocator = logoLocator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technology that = (Technology) o;
        return id.equals(that.id) && name.equals(that.name) && technologyType == that.technologyType && Objects.equals(description, that.description) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(logoLocator, that.logoLocator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, technologyType, description, shortDescription, logoLocator);
    }

    @Override
    public String toString() {
        return "Technology{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + technologyType +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", logoLocator='" + logoLocator + '\'' +
                '}';
    }
}
