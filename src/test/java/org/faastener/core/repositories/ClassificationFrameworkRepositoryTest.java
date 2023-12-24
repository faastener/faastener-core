package org.faastener.core.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.faastener.core.model.common.TechnologyType;
import org.faastener.core.model.entities.ClassificationFrameworkEntity;
import org.faastener.core.model.entities.ClassificationFrameworkEntity.CriteriaGroupingEntity;
import org.faastener.core.model.entities.ClassificationFrameworkEntity.CriterionTypeEntity;
import org.faastener.core.model.entities.ClassificationFrameworkEntity.FrameworkViewEntity;
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
class ClassificationFrameworkRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ClassificationFrameworkRepository repository;


    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    @Test
    @MongoDataFile(value = "framework.json", classType = ClassificationFrameworkEntity.class, collectionName = "frameworks")
    void testSave() {
        ClassificationFrameworkEntity framework1 = initTestFramework(2, 3, 3);
        ClassificationFrameworkEntity savedFramework1 = repository.save(framework1);

        Optional<ClassificationFrameworkEntity> loadedFramework1 = repository.findById(savedFramework1.getId());

        Assertions.assertTrue(loadedFramework1.isPresent());

        loadedFramework1.ifPresent(f1 -> {
            Assertions.assertEquals(framework1.getId(), f1.getId());
            Assertions.assertEquals(framework1.getName(), f1.getName());
            Assertions.assertEquals(framework1.getDescription(), f1.getDescription());
            Assertions.assertEquals(framework1.getVersion(), f1.getVersion());
            Assertions.assertEquals(2, f1.getFrameworkViews().size(), "There should be 2 views");
        });
    }

    @Test
    @MongoDataFile(value = "framework.json", classType = ClassificationFrameworkEntity.class, collectionName = "frameworks")
    void testSaveAll() {
        ClassificationFrameworkEntity framework1 = initTestFramework(2, 3, 3);
        ClassificationFrameworkEntity framework2 = initTestFramework(1, 2, 4);

        List<ClassificationFrameworkEntity> savedFrameworks = repository.saveAll(List.of(framework1, framework2));
        List<ClassificationFrameworkEntity> loadedFrameworks = repository.findAll();

        Assertions.assertEquals(savedFrameworks.size(), loadedFrameworks.size());
    }

    @Test
    @MongoDataFile(value = "framework.json", classType = ClassificationFrameworkEntity.class, collectionName = "frameworks")
    void testFindByIdSuccess() {
        Optional<ClassificationFrameworkEntity> framework = repository.findById("fwFaaS");
        Assertions.assertTrue(framework.isPresent(), "Framework with the given ID should be present");
        framework.ifPresent(fw -> {
            Assertions.assertEquals("fwFaaS", fw.getId(), "Framework ID should be fwFaaS");
            Assertions.assertEquals(TechnologyType.FAAS, fw.getTechnologyType(), "Framework type should be FAAS");
            Assertions.assertEquals(2, fw.getFrameworkViews().size(), "Framework with ID fw-faas should have 2 views");
        });
    }

    @Test
    @MongoDataFile(value = "framework.json", classType = ClassificationFrameworkEntity.class, collectionName = "frameworks")
    void testFindByIdFailure() {
        Optional<ClassificationFrameworkEntity> framework = repository.findById("absentID");
        Assertions.assertFalse(framework.isPresent(), "No framework with ID absentID should exist");
    }

    private ClassificationFrameworkEntity initTestFramework(int numViews, int numGroupings, int criteriaTypesPerGrouping) {
        ClassificationFrameworkEntity framework = new ClassificationFrameworkEntity();
        framework.setId("test-fw-id");
        framework.setName("test-fw-name");
        framework.setDescription("test-desc");
        framework.setVersion("1.0.0");

        framework.setFrameworkViews(initTestViews(numViews, numGroupings, criteriaTypesPerGrouping));

        return framework;
    }

    private List<FrameworkViewEntity> initTestViews(int numViews, int groupingsPerView, int criteriaTypesPerGrouping) {
        List<FrameworkViewEntity> views = new ArrayList<>();

        for (int view = 1; view <= numViews; view++) {
            FrameworkViewEntity v = new FrameworkViewEntity();
            v.setId("test-view-id-" + view);
            v.setName("test-view-" + view);
            v.setDescription("test-view-desc-" + view);
            v.setCriteriaGroupings(initTestGroupings(groupingsPerView, criteriaTypesPerGrouping));

            views.add(v);
        }

        return views;
    }

    private List<CriteriaGroupingEntity> initTestGroupings(int numGroupings, int criteriaTypesPerGrouping) {
        List<CriteriaGroupingEntity> groupings = new ArrayList<>();

        for (int grouping = 1; grouping <= numGroupings; grouping++) {
            CriteriaGroupingEntity g = new CriteriaGroupingEntity();
            g.setId("test-grouping-id-" + grouping);
            g.setName("test-grouping-name-" + grouping);
            g.setLocator("test-grouping-locator-" + grouping);
            g.setCriteria(initTestCriteriaTypes(criteriaTypesPerGrouping));

            groupings.add(g);
        }

        return groupings;
    }

    private List<CriterionTypeEntity> initTestCriteriaTypes(int numCrtieriaTypes) {
        List<CriterionTypeEntity> criteriaTypes = new ArrayList<>();

        for (int type = 1; type <= numCrtieriaTypes; type++) {
            CriterionTypeEntity ct = new CriterionTypeEntity();
            ct.setId("test-criterion-type-id-" + type);
            ct.setName("test-criterion-type-name-id-" + type);
            ct.setDescription("test-criterion-type-desc-id-" + type);
            ct.setExampleValues(Collections.singletonList("single example"));

            criteriaTypes.add(ct);
        }

        return criteriaTypes;
    }
}




