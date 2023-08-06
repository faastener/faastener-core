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
import org.faastener.core.model.entities.InformationSectionEntity;
import org.faastener.core.repositories.InformationResourceRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "resources-initializer", order = "5", author = "v-yussupov")
public class ResourcesInitializerChangeUnit {
    private final MongoTemplate template;

    public ResourcesInitializerChangeUnit(MongoTemplate template) {
        this.template = template;
    }

    @BeforeExecution
    public void before() {
        template.createCollection("resources");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("resources");
    }

    @Execution
    public void migrationMethod() {
        // create Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<InformationSectionEntity> resources = mapper.readValue(new ClassPathResource("data/cu4-resources.json").getFile(), new TypeReference<>() {
            });
            template.insertAll(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RollbackExecution
    public void rollback(InformationResourceRepository repository) {
        repository.deleteAll();
    }
}
