package org.faastener.core.migration;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.faastener.core.model.Technology;
import org.faastener.core.repositories.TechnologyRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "technology-initializer", order = "2", author = "v-yussupov")
public class TechnologyInitializerChangeUnit {
    private final MongoTemplate template;

    public TechnologyInitializerChangeUnit(MongoTemplate template) {
        this.template = template;
    }

    @BeforeExecution
    public void before() {
        template.createCollection("technologies");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("technologies");
    }

    @Execution
    public void migrationMethod() {
        // create Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Technology> technologies = mapper.readValue(new ClassPathResource("data/cu2-technologies.json").getFile(), new TypeReference<>() {
            });
            template.insertAll(technologies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RollbackExecution
    public void rollback(TechnologyRepository repository) {
        repository.deleteAll();
    }
}
