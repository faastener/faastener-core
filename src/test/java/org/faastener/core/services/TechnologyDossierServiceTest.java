package org.faastener.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.faastener.core.model.Technology;
import org.faastener.core.model.TechnologyDossier;
import org.faastener.core.model.TechnologyReviewEntry;
import org.faastener.core.model.TechnologyType;
import org.faastener.core.repositories.TechnologyDossierRepository;
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
class TechnologyDossierServiceTest {
    @Autowired
    private TechnologyDossierService service;

    @MockBean
    private TechnologyDossierRepository repository;

    @Test
    @DisplayName("Test findById - Success")
    void testFindByIdSuccess() {
        Technology mockTechnology = new Technology("techId", "techName", TechnologyType.FAAS, "desc", "short desc", "logo locator");
        List<TechnologyReviewEntry> mockReviewEntries = new ArrayList<>();
        mockReviewEntries.add(new TechnologyReviewEntry("type1", new ArrayList<>()));
        TechnologyDossier mockDossier = new TechnologyDossier("some-dossier", null, mockTechnology, mockReviewEntries);

        doReturn(Optional.of(mockDossier)).when(repository).findById("techId");
        Optional<TechnologyDossier> returned = service.findById("techId");

        Assertions.assertTrue(returned.isPresent(), "Technology dossier was not found");
        Assertions.assertSame(returned.get(), mockDossier, "Technology dossier should be the same");
    }

    @Test
    @DisplayName("Test findById - Not Found")
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById("someId");
        Optional<TechnologyDossier> returned = service.findById("someId");

        Assertions.assertFalse(returned.isPresent(), "No dossier should be found");
    }
}