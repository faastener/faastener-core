package org.faastener.core.controllers;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.faastener.core.model.TechnologyDossier;
import org.faastener.core.services.TechnologyDossierService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TechnologyDossierController.class)
@TestPropertySource(properties = "mongock.enabled=false")
class TechnologyDossierControllerTest {
    @MockBean
    private TechnologyDossierService dossierService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/dossiers/{id} - Found")
    void testGetDossierByIdFound() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TechnologyDossier mockDossier = mapper.readValue(new ClassPathResource("data/cu2-td-fn.json").getFile(), TechnologyDossier.class);
        doReturn(Optional.of(mockDossier)).when(dossierService).findById("fn-1");

        mockMvc.perform(get("/api/v1/dossiers/{id}", "fn-1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/dossiers/fn-1"))

                .andExpect(jsonPath("$.id", is("fn-1")))
                .andExpect(jsonPath("$.technology.technologyType", is("FAAS")))
                .andExpect(jsonPath("$.reviewedCriteria.length()", is(56)));
    }

    @Test
    @DisplayName("GET /api/v1/dossiers/{id} - Not Found")
    void testGetDossierByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(dossierService).findById("fn-1");

        // Execute the GET request
        mockMvc.perform(get("/api/v1/dossiers/{id}", "fn-1"))

                // Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }
}