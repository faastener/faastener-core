package org.faastener.core.migration;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mongock.api.annotations.BeforeExecution;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackBeforeExecution;
import io.mongock.api.annotations.RollbackExecution;
import org.faastener.core.model.entities.TechnologyDossierEntity;
import org.faastener.core.repositories.TechnologyDossierRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@ChangeUnit(id = "dossier-initializer", order = "3", author = "v-yussupov")
public class DossierInitializerChangeUnit {
    private final MongoTemplate template;

    public DossierInitializerChangeUnit(MongoTemplate template) {
        this.template = template;
    }

    @BeforeExecution
    public void before() {
        template.createCollection("dossiers");
    }

    @RollbackBeforeExecution
    public void rollbackBeforeExecution() {
        template.dropCollection("dossiers");
    }

    @Execution
    public void migrationMethod() {
        // create Object Mapper
        ObjectMapper mapper = new ObjectMapper();

        try {
            TechnologyDossierEntity td = mapper.readValue(new ClassPathResource("data/cu2-td-openwhisk.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-lambda.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-azurefunctions.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-fission.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-fn.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-gcf.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-knative.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-kubeless.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-nuclio.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");

            td = mapper.readValue(new ClassPathResource("data/cu2-td-openfaas.json").getFile(), TechnologyDossierEntity.class);
            template.save(td, "dossiers");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RollbackExecution
    public void rollback(TechnologyDossierRepository repository) {
        repository.deleteAll();
    }
}
