package org.faastener.core.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.domain.Technology;
import org.faastener.core.model.domain.TechnologyDossier;
import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.faastener.core.model.entities.TechnologyEntity;
import org.faastener.core.repositories.TechnologyDossierRepository;
import org.faastener.core.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnologyServiceImpl implements TechnologyService {
    private final TechnologyRepository technologyRepository;
    private final TechnologyDossierRepository technologyDossierRepository;
    private final EntityMapper entityMapper;

    @Override
    public Optional<Technology> findTechnologyById(String id, boolean dossierRequested) {
        Optional<TechnologyEntity> res = technologyRepository.findById(id);
        if (dossierRequested && res.isPresent()) {
            Technology tech = entityMapper.toTechnologyDomainModel(res.get());
            Optional<TechnologyDossierEntity> dossier = technologyDossierRepository.findByTechnologyId(tech.getId());
            dossier.ifPresent(technologyDossierEntity ->
                    tech.setDossier(entityMapper.toTechnologyDossierDomainModel(technologyDossierEntity))
            );

            return Optional.of(tech);
        }

        return res.map(entityMapper::toTechnologyDomainModel);
    }

    @Override
    public List<Technology> findTechnologies(boolean dossierRequested) {
        List<Technology> res = technologyRepository.findAll()
                .stream()
                .map(entityMapper::toTechnologyDomainModel)
                .collect(Collectors.toList());

        if (dossierRequested) {
            Map<String, TechnologyDossier> dossiers = technologyDossierRepository.findAllByTechnologyIdIn(
                            res.stream().map(Technology::getId).collect(Collectors.toList()))
                    .stream()
                    .map(entityMapper::toTechnologyDossierDomainModel)
                    .collect(Collectors.toMap(TechnologyDossier::getTechnologyId, Function.identity()));

            res.forEach(tech -> tech.setDossier(dossiers.get(tech.getId())));
        }

        return res;
    }

    @Override
    public List<Technology> findTechnologies(String filter, boolean dossierRequested) throws RSQLParserException {
        Node rootNode = new RSQLParser().parse(filter);

        Map<String, TechnologyDossier> dossiers = technologyRepository.searchDossiers(rootNode)
                .stream()
                .map(entityMapper::toTechnologyDossierDomainModel)
                .collect(Collectors.toMap(TechnologyDossier::getTechnologyId, Function.identity()));

        List<Technology> res = technologyRepository.findAllById(
                        dossiers.values().stream().map(TechnologyDossier::getTechnologyId).collect(Collectors.toList())
                )
                .stream()
                .map(entityMapper::toTechnologyDomainModel)
                .toList();
        if (dossierRequested) {
            res.forEach(tech -> tech.setDossier(dossiers.get(tech.getId())));
        }

        return res;
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
