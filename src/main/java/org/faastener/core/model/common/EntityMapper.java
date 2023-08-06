package org.faastener.core.model.common;

import lombok.RequiredArgsConstructor;
import org.faastener.core.model.domain.ClassificationFramework;
import org.faastener.core.model.domain.FilterConfiguration;
import org.faastener.core.model.domain.InformationSection;
import org.faastener.core.model.domain.Technology;
import org.faastener.core.model.domain.TechnologyDossier;
import org.faastener.core.model.entities.ClassificationFrameworkEntity;
import org.faastener.core.model.entities.FilterConfigurationEntity;
import org.faastener.core.model.entities.InformationSectionEntity;
import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.faastener.core.model.entities.TechnologyEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityMapper {
    private final ModelMapper modelMapper;

    public ClassificationFrameworkEntity toFrameworkEntity(ClassificationFramework framework) {
        return modelMapper.map(framework, ClassificationFrameworkEntity.class);
    }

    public ClassificationFramework toFrameworkDomainModel(ClassificationFrameworkEntity entity) {
        return modelMapper.map(entity, ClassificationFramework.class);
    }

    public FilterConfigurationEntity toFilterConfigurationEntity(FilterConfiguration filterConfiguration) {
        return modelMapper.map(filterConfiguration, FilterConfigurationEntity.class);
    }

    public FilterConfiguration toFilterConfigurationDomainModel(FilterConfigurationEntity entity) {
        return modelMapper.map(entity, FilterConfiguration.class);
    }

    public InformationSectionEntity toInformationSectionEntity(InformationSection informationSection) {
        return modelMapper.map(informationSection, InformationSectionEntity.class);
    }

    public InformationSection toInformationSectionDomainModel(InformationSectionEntity entity) {
        return modelMapper.map(entity, InformationSection.class);
    }

    public TechnologyEntity toTechnologyEntity(Technology technology) {
        return modelMapper.map(technology, TechnologyEntity.class);
    }

    public Technology toTechnologyDomainModel(TechnologyEntity entity) {
        return modelMapper.map(entity, Technology.class);
    }

    public TechnologyDossierEntity toTechnologyDossierEntity(TechnologyDossier technologyDossier) {
        return modelMapper.map(technologyDossier, TechnologyDossierEntity.class);
    }

    public TechnologyDossier toTechnologyDossierDomainModel(TechnologyDossierEntity entity) {
        return modelMapper.map(entity, TechnologyDossier.class);
    }
}
