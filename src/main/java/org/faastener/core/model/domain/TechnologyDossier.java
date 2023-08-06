package org.faastener.core.model.domain;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnologyDossier {
    public String id;
    public String technologyId;
    public Date reviewDate;
    public Map<String, List<AbstractReviewEntry>> reviewedCriteria;

    public static abstract class AbstractReviewEntry {
        public List<URL> references;
        public String comment;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StringReviewEntry extends AbstractReviewEntry {
        public String value;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IntegerReviewEntry extends AbstractReviewEntry {
        public Integer value;
    }
}
