package org.faastener.core.models;

import java.util.Date;
import java.util.List;

public class TechnologyDossier {
    public String id;
    public Date reviewDate;
    public List<TechnologyReviewEntry> criteria;
}
