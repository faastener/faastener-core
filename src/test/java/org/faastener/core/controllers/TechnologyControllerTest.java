package org.faastener.core.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.faastener.core.model.common.EntityMapper;
import org.faastener.core.model.domain.Technology;
import org.faastener.core.model.entities.TechnologyEntity;
import org.faastener.core.services.TechnologyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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

@TestPropertySource(properties = "mongock.enabled=false")
@SpringBootTest
@AutoConfigureMockMvc
class TechnologyControllerTest {
    @MockBean
    private TechnologyService technologiesService;
    @Autowired
    private MockMvc mockMvc;
    private final EntityMapper entityMapper = new EntityMapper(new ModelMapper());

    @Test
    @DisplayName("GET /api/technologies/{id} - Found")
    void testGetTechnologyDossierByTechnologyIdFound() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Stream<TechnologyEntity> mockEntityStream = mapper.readValue(new ClassPathResource("data/cu2-technologies.json").getFile(), new TypeReference<List<TechnologyEntity>>() {
                })
                .stream()
                .filter(technology -> technology.getId().equals("fn-project"));

        Technology mockTechnology = entityMapper.toTechnologyDomainModel(mockEntityStream.iterator().next());

        doReturn(Optional.of(mockTechnology)).when(technologiesService).findTechnologyById("fn-project", false);

        mockMvc.perform(get("/api/technologies/{id}", "fn-project"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/technologies/fn-project"))

                .andExpect(jsonPath("$.id", is("fn-project")));
        //.andExpect(jsonPath("$.reviewedCriteria.length()", is(56)));
    }

    @Test
    @DisplayName("GET /api/technologies/{id} - Not Found")
    void testGetDossierByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(technologiesService).findTechnologyById("fn-1", false);

        // Execute the GET request
        mockMvc.perform(get("/api/technologies/{id}", "fn-1"))

                // Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }
}
