package org.faastener.core.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "infosections")
public class InformationSection {
    public String id;
    public String title;
    public String description;
    public TechnologyType technologyType;
    public List<InformationResource> resources;
}


