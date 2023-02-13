package org.faastener.core.migration;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.repositories.ClassificationFrameworkRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "framework-initializer", order = "1", author = "v-yussupov")
public class FrameworkInitializerChangeUnit {
    private final MongoTemplate template;

    public FrameworkInitializerChangeUnit(MongoTemplate template) {
        this.template = template;
    }

    @BeforeExecution
    public void before() {
        template.createCollection("frameworks");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("frameworks");
    }

    @Execution
    public void migrationMethod() {
        // create Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        try {
            ClassificationFramework fw = mapper.readValue(new ClassPathResource("data/cu1-frameworks.json").getFile(), ClassificationFramework.class);
            //ClassificationFramework fw = new ClassificationFramework("asda", "some name", "desc", TechnologyType.FAAS, new ArrayList<>());

            template.save(fw, "frameworks");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RollbackExecution
    public void rollback(ClassificationFrameworkRepository repository) {
        repository.deleteAll();
    }
}
