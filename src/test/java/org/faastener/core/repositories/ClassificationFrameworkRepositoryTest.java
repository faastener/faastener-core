package org.faastener.core.repositories;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.Category;
import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.model.TechnologyType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@TestPropertySource(properties = "mongock.enabled=false")
@Category("integration")
class ClassificationFrameworkRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ClassificationFrameworkRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void beforeEach() throws Exception {
        ClassificationFramework framework = mapper.readValue(new ClassPathResource("data/cu1-frameworks.json").getFile(), ClassificationFramework.class);

        mongoTemplate.save(framework);
    }

    @AfterEach
    void afterEach() {
        mongoTemplate.dropCollection("frameworks");
    }

    @Test
    void testFindByIdSuccess() {
        Optional<ClassificationFramework> framework = repository.findById("fw-faas");
        Assertions.assertTrue(framework.isPresent(), "Framework with the given ID should be present");
        framework.ifPresent(fw -> {
            Assertions.assertEquals("fw-faas", fw.getId(), "Framework ID should be fw-faas");
            Assertions.assertEquals(TechnologyType.FAAS, fw.getTechnologyType(), "Framework type should be FAAS");
            Assertions.assertEquals(2, fw.getFrameworkViews().size(), "Framework with ID fw-faas should have 2 views");
        });
    }

    @Test
    void testFindByIdFailure() {
        Optional<ClassificationFramework> framework = repository.findById("absentID");
        Assertions.assertFalse(framework.isPresent(), "No framework with ID absentID should exist");
    }
}