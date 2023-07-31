package org.faastener.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.faastener.core.model.SearchCriterion;
import org.faastener.core.model.Technology;
import org.faastener.core.model.TechnologyDossier;
import org.faastener.core.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository repository;

    public TechnologyServiceImpl(TechnologyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Technology> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Technology> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TechnologyDossier> findAll(String filter) {
        List<SearchCriterion> params = new ArrayList<SearchCriterion>();

        Pattern pattern = Pattern.compile("(\\w+?)([:<>])(\\w+?),");
        Matcher matcher = pattern.matcher(filter + ",");
        while (matcher.find()) {
            params.add(new SearchCriterion(matcher.group(1),
                    matcher.group(2), matcher.group(3)));
        }

        return repository.searchDossiers(params);
    }

    @Override
    public Technology save(Technology technology) {
        return null;
    }

    @Override
    public Technology update(Technology technology) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
