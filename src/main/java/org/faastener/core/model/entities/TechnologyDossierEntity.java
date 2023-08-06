package org.faastener.core.model.entities;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dossiers")
public class TechnologyDossierEntity {
    @Id
    public String id;
    public String technologyId;
    public Date reviewDate;
    public Map<String, List<TechnologyReviewEntryEntity<?>>> reviewedCriteria;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TechnologyReviewEntryEntity<T> {
        public List<URL> references;
        public String comment;
        public T value;
    }
}
