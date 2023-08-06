package org.faastener.core.migration;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.faastener.core.model.entities.FilterConfigurationEntity;
import org.faastener.core.repositories.TechnologyDossierRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "filter-initializer", order = "4", author = "v-yussupov")
public class FilterInitializerChangeUnit {
    private final MongoTemplate template;

    public FilterInitializerChangeUnit(MongoTemplate template) {
        this.template = template;
    }

    @BeforeExecution
    public void before() {
        template.createCollection("filters");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("filters");
    }

    @Execution
    public void migrationMethod() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            FilterConfigurationEntity f = mapper.readValue(new ClassPathResource("data/cu3-filters.json").getFile(), FilterConfigurationEntity.class);
            template.save(f, "filters");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RollbackExecution
    public void rollback(TechnologyDossierRepository repository) {
        repository.deleteAll();
    }
}
