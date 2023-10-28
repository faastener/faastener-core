package org.faastener.core.services;

import java.util.HashMap;

import org.faastener.core.repositories.TechnologyDossierRepository;
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
class TechnologyServiceTest {
    @Autowired
    private TechnologyService service;

    @MockBean
    private TechnologyDossierRepository repository;

    /*@Test
    @DisplayName("Test findTechnologyById - Success")
    void testFindTechnologyByIdSuccess() {
        Map<String, List<TechnologyReviewEntry<?>>> mockReview = new HashMap<>();
        List<TechnologyReviewEntry<?>> mockReviewEntries = new ArrayList<>();
        mockReviewEntries.add(new TechnologyReviewEntry<>(null, "comment", "entry_value"));
        mockReview.put("type1", mockReviewEntries);
        TechnologyDossier mockDossier = new TechnologyDossier("some-dossier", "some-tech-ref", null, mockReview);

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
    }*/
}
