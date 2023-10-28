package org.faastener.core.repositories;

import java.util.List;

import cz.jirutka.rsql.parser.ast.Node;
import org.faastener.core.model.entities.TechnologyDossierEntity;

public interface TechnologyDossierSearch {
    List<TechnologyDossierEntity> searchDossiers(Node rootQuery);

    List<String> searchDossierIds(Node rootQuery);
}
