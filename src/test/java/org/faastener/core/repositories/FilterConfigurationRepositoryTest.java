package org.faastener.core.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.faastener.core.model.common.FilterType;
import org.faastener.core.model.common.TechnologyType;
import org.faastener.core.model.entities.FilterConfigurationEntity;
import org.faastener.core.model.entities.FilterConfigurationEntity.CriterionFilterEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

@DataMongoTest
@ExtendWith(MongoRepositoryTestExtension.class)
@TestPropertySource(properties = "mongock.enabled=false")
class FilterConfigurationRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private FilterConfigurationRepository repository;

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Test
    @MongoDataFile(value = "filters.json", classType = FilterConfigurationEntity.class, collectionName = "filters")
    void testSave() {
        FilterConfigurationEntity filterConfig = initTestFilterConfiguration(3);
        FilterConfigurationEntity savedFilterConfig = repository.save(filterConfig);

        Optional<FilterConfigurationEntity> loadedFilterConfig = repository.findById(savedFilterConfig.getId());

        Assertions.assertTrue(loadedFilterConfig.isPresent());

        loadedFilterConfig.ifPresent(f1 -> {
            Assertions.assertEquals(filterConfig.getId(), loadedFilterConfig.get().getId());
            Assertions.assertEquals(filterConfig.getName(), loadedFilterConfig.get().getName());
            Assertions.assertEquals(filterConfig.getTechnologyType(), loadedFilterConfig.get().getTechnologyType());
            Assertions.assertEquals(filterConfig.getFilters().size(), loadedFilterConfig.get().getFilters().size(), "There should be 3 filters");
        });
    }

    @Test
    @MongoDataFile(value = "filters.json", classType = FilterConfigurationEntity.class, collectionName = "filters")
    void testFindByIdSuccess() {
        Optional<FilterConfigurationEntity> filterConfig = repository.findById("faas-filter");
        Assertions.assertTrue(filterConfig.isPresent(), "Filter config with the given ID should be present");
        filterConfig.ifPresent(config -> {
            Assertions.assertEquals("faas-filter", config.getId(), "Filter config ID should be fwFaaS");
            Assertions.assertEquals(TechnologyType.FAAS, config.getTechnologyType(), "Filter config type should be FAAS");
            Assertions.assertEquals(49, config.getFilters().size(), "Filter config with ID faas-filter should have 2 views");
        });
    }

    @Test
    @MongoDataFile(value = "filters.json", classType = FilterConfigurationEntity.class, collectionName = "filters")
    void testFindByIdFailure() {
        Optional<FilterConfigurationEntity> filterConfig = repository.findById("absentID");
        Assertions.assertFalse(filterConfig.isPresent(), "No filter config  with ID absentID should exist");
    }

    private FilterConfigurationEntity initTestFilterConfiguration(int numFilters) {
        FilterConfigurationEntity fc = new FilterConfigurationEntity();
        fc.setId("test-filter-id");
        fc.setName("test-filter-name");
        fc.setTechnologyType(TechnologyType.FAAS);
        fc.setFilters(initTestFilters(numFilters));

        return fc;
    }

    private List<CriterionFilterEntity> initTestFilters(int numFilters) {
        List<CriterionFilterEntity> res = new ArrayList<>();

        for (int filter = 1; filter <= numFilters; filter++) {
            CriterionFilterEntity f = new CriterionFilterEntity();
            f.setFilterType(FilterType.BOOL);
            f.setCriterionTypeId("test-criterion-type-id");
            f.setDisplayName("test-filter-name-" + filter);
            res.add(f);
        }

        return res;
    }
}




