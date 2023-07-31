package org.faastener.core.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dossiers")
public class TechnologyDossier {
    @Id
    public String id;
    public Date reviewDate;
    public Map<String, List<TechnologyReviewEntry<?>>> reviewedCriteria;

    public TechnologyDossier() {
    }

    public TechnologyDossier(String id, Date reviewDate, Map<String, List<TechnologyReviewEntry<?>>> reviewedCriteria) {
        this.id = id;
        this.reviewDate = reviewDate;
        this.reviewedCriteria = reviewedCriteria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Map<String, List<TechnologyReviewEntry<?>>> getReviewedCriteria() {
        return reviewedCriteria;
    }

    public void setReviewedCriteria(Map<String, List<TechnologyReviewEntry<?>>> reviewedCriteria) {
        this.reviewedCriteria = reviewedCriteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnologyDossier that = (TechnologyDossier) o;
        return id.equals(that.id) && reviewDate.equals(that.reviewDate) && Objects.equals(reviewedCriteria, that.reviewedCriteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewDate, reviewedCriteria);
    }

    @Override
    public String toString() {
        return "TechnologyDossier{" +
                "id='" + id + '\'' +
                ", reviewDate=" + reviewDate +
                ", criteria=" + reviewedCriteria +
                '}';
    }
}
