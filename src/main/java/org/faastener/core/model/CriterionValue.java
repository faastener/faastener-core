package org.faastener.core.model;

import java.net.URL;
import java.util.List;
import java.util.Objects;

public class CriterionValue<T> {
    public List<URL> references;
    public String comment;
    public T value;

    public CriterionValue() {
    }

    public CriterionValue(List<URL> references, String comment, T value) {
        this.references = references;
        this.comment = comment;
        this.value = value;
    }

    public List<URL> getReferences() {
        return references;
    }

    public void setReferences(List<URL> references) {
        this.references = references;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriterionValue<?> that = (CriterionValue<?>) o;
        return Objects.equals(references, that.references) && Objects.equals(comment, that.comment) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(references, comment, value);
    }

    @Override
    public String toString() {
        return "CriterionValue{" +
                "references=" + references +
                ", comment='" + comment + '\'' +
                ", value=" + value +
                '}';
    }
}
