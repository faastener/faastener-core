package org.faastener.core.services;

import java.util.Optional;

import org.faastener.core.model.domain.FilterConfiguration;
import org.faastener.core.repositories.FilterConfigurationRepository;
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
class FilterConfigurationServiceTest {
    @Autowired
    private FilterConfigurationService service;

    @MockBean
    private FilterConfigurationRepository repository;

    /*@Test
    @DisplayName("Test findById - Success")
    void testFindByIdSuccess() {
        List<CriterionFilter> mockFitlers = new ArrayList<>();
        mockFitlers.add(new CriterionFilter("filterId1", "filterId1 Name", FilterType.CONTAINS_ANY));
        mockFitlers.add(new CriterionFilter("filterId2", "filterId2 Name", FilterType.CONTAINS_ONE));

        FilterConfiguration mockFilterConfiguration = new FilterConfiguration("faas-filter", TechnologyType.FAAS, "some name", mockFitlers);

        doReturn(Optional.of(mockFilterConfiguration)).when(repository).findById("faas-filter");

        Optional<FilterConfiguration> returned = service.findById("faas-filter");

        Assertions.assertTrue(returned.isPresent(), "Filter configuration was not found");
        Assertions.assertSame(returned.get(), mockFilterConfiguration, "Filter configuration should be the same");
    }*/

    @Test
    @DisplayName("Test findById - Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById("someId");
        Optional<FilterConfiguration> returned = service.findById("someId");

        Assertions.assertFalse(returned.isPresent(), "No filter configuration should be found");
    }
}
