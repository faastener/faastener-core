package org.faastener.core.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "frameworks")
public class ClassificationFramework {
    @Id
    public String id;
    public String name;
    public String description;
    public TechnologyType technologyType;
    public List<ViewCombination> viewCombinations;
}
