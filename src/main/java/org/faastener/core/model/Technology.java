package org.faastener.core.model;

import java.util.Objects;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "technologies")
public class Technology {
    public String id;
    public String technologyName;
    public TechnologyType technologyType;
    public String logoLocation;
    public String shortDescription;
    public String description;

    public Technology() {
    }

    public Technology(String id, String technologyName, TechnologyType technologyType, String description, String shortDescription, String logoLocation) {
        this.id = id;
        this.technologyName = technologyName;
        this.technologyType = technologyType;
        this.description = description;
        this.shortDescription = shortDescription;
        this.logoLocation = logoLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
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

    public String getLogoLocation() {
        return logoLocation;
    }

    public void setLogoLocation(String logoLocation) {
        this.logoLocation = logoLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technology that = (Technology) o;
        return id.equals(that.id) && technologyName.equals(that.technologyName) && technologyType == that.technologyType && Objects.equals(description, that.description) && Objects.equals(shortDescription, that.shortDescription) && Objects.equals(logoLocation, that.logoLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, technologyName, technologyType, description, shortDescription, logoLocation);
    }

    @Override
    public String toString() {
        return "Technology{" +
                "id='" + id + '\'' +
                ", technologyName='" + technologyName + '\'' +
                ", technologiesType=" + technologyType +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", logoLocator='" + logoLocation + '\'' +
                '}';
    }
}
