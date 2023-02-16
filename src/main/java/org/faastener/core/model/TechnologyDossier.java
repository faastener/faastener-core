package org.faastener.core.model;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dossiers")
public class TechnologyDossier {
    @Id
    public String id;
    public Date reviewDate;
    public Technology technology;
    public List<TechnologyReviewEntry> criteria;

    public TechnologyDossier() {
    }

    public TechnologyDossier(String id, Date reviewDate, List<TechnologyReviewEntry> criteria) {
        this.id = id;
        this.reviewDate = reviewDate;
        this.criteria = criteria;
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

    public List<TechnologyReviewEntry> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<TechnologyReviewEntry> criteria) {
        this.criteria = criteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TechnologyDossier that = (TechnologyDossier) o;
        return id.equals(that.id) && reviewDate.equals(that.reviewDate) && Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reviewDate, criteria);
    }

    @Override
    public String toString() {
        return "TechnologyDossier{" +
                "id='" + id + '\'' +
                ", reviewDate=" + reviewDate +
                ", criteria=" + criteria +
                '}';
    }
}
