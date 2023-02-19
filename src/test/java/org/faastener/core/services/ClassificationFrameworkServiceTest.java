package org.faastener.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.model.CriteriaGrouping;
import org.faastener.core.model.CriterionType;
import org.faastener.core.model.FrameworkView;
import org.faastener.core.model.TechnologyType;
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

    @MockBean
    private ClassificationFrameworkRepository repository;

    @Test
    @DisplayName("Test findById - Success")
    void testFindByIdSuccess() {
        List<CriterionType> mockCriteria = new ArrayList<>();
        mockCriteria.add(new CriterionType("criterion1", "criterion type 1", "criterion type 1 description", Collections.emptyList()));
        mockCriteria.add(new CriterionType("criterion1", "criterion type 2", "criterion type 2 description", Collections.emptyList()));
        CriteriaGrouping mockGrouping = new CriteriaGrouping("groupingId", "test grouping", "grouping description", Collections.emptyList(), mockCriteria);
        FrameworkView mockView = new FrameworkView("viewId", "test view", "view description", Collections.singletonList(mockGrouping));
        ClassificationFramework mockFramework = new ClassificationFramework("frameworkId", "test", TechnologyType.FAAS, "1.0", "some description", Collections.singletonList(mockView));

        doReturn(Optional.of(mockFramework)).when(repository).findById("frameworkId");

        Optional<ClassificationFramework> returned = service.findById("frameworkId");

        Assertions.assertTrue(returned.isPresent(), "Classification framework was not found");
        Assertions.assertSame(returned.get(), mockFramework, "Classification framework should be the same");
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