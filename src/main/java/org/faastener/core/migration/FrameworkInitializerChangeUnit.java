package org.faastener.core.migration;

import java.util.ArrayList;

import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.faastener.core.repositories.ClassificationFrameworkRepository;
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
    public void migrationMethod(ClassificationFrameworkRepository repository) {
        repository.saveAll(new ArrayList<>());
    }

    @RollbackExecution
    public void rollback(ClassificationFrameworkRepository repository) {
        repository.deleteAll();
    }
}
