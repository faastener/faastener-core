package org.faastener.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.common.TechnologyType;
import org.faastener.core.model.domain.ClassificationFramework;
import org.faastener.core.model.entities.ClassificationFrameworkEntity;
import org.faastener.core.model.entities.ClassificationFrameworkEntity.CriteriaGroupingEntity;
import org.faastener.core.model.entities.ClassificationFrameworkEntity.CriterionTypeEntity;
import org.faastener.core.model.entities.ClassificationFrameworkEntity.FrameworkViewEntity;
import org.faastener.core.repositories.ClassificationFrameworkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClassificationFrameworkServiceTest {

    @Autowired
    private ClassificationFrameworkService service;

    @Autowired
    private EntityMapper entityMapper;

    @MockBean
    private ClassificationFrameworkRepository repository;

    @Test
    @DisplayName("Test findById - Success")
    void testFindByIdSuccess() {
        List<CriterionTypeEntity> mockCriteria = new ArrayList<>();
        mockCriteria.add(new CriterionTypeEntity("criterion1", "criterion type 1", "criterion type 1 description", Collections.emptyList()));
        mockCriteria.add(new CriterionTypeEntity("criterion1", "criterion type 2", "criterion type 2 description", Collections.emptyList()));
        CriteriaGroupingEntity mockGrouping = new CriteriaGroupingEntity("groupingId", "test grouping", "grouping description", Collections.emptyList(), mockCriteria);
        FrameworkViewEntity mockView = new FrameworkViewEntity("viewId", "test view", "view description", Collections.singletonList(mockGrouping));
        ClassificationFrameworkEntity mockFrameworkEntity = new ClassificationFrameworkEntity("frameworkId", "test", TechnologyType.FAAS, "1.0", "some description", Collections.singletonList(mockView));

        doReturn(Optional.of(mockFrameworkEntity)).when(repository).findById("frameworkId");

        Optional<ClassificationFramework> returned = service.findById("frameworkId");

        Assertions.assertTrue(returned.isPresent(), "Classification framework was not found");
        Assertions.assertEquals(returned.get().getId(), mockFrameworkEntity.getId(), "Classification framework should have the same id");
        Assertions.assertEquals(returned.get().getName(), mockFrameworkEntity.getName(), "Classification framework should have the same name");
        Assertions.assertEquals(returned.get().getTechnologyType(), mockFrameworkEntity.getTechnologyType(), "Classification framework should be of the same technology type");
        Assertions.assertEquals(returned.get().getFrameworkViews().size(), mockFrameworkEntity.getFrameworkViews().size(), "Classification framework should have the same number of views");
    }

    @Test
    @DisplayName("Test findById - Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById("someId");
        Optional<ClassificationFramework> returned = service.findById("someId");

        Assertions.assertFalse(returned.isPresent(), "No classification framework should be found");
    }

    @Test
    @DisplayName("Test findAll - Success")
    void findAll() {

    }

    @Test
    void save() {
    }
}
