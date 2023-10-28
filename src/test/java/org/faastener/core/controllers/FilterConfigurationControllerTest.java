package org.faastener.core.controllers;

import java.util.Optional;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.faastener.core.model.domain.FilterConfiguration;
import org.faastener.core.model.entities.FilterConfigurationEntity;
import org.faastener.core.services.FilterConfigurationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "mongock.enabled=false")
class FilterConfigurationControllerTest {
    @MockBean
    private FilterConfigurationService filterConfigurationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/filters/{id} - Found")
    void testGetFilterConfigurationByIdFound() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
        FilterConfiguration mockFilterConfiguration = mapper.readValue(new ClassPathResource("data/cu3-filters.json").getFile(), FilterConfiguration.class);
        doReturn(Optional.of(mockFilterConfiguration)).when(filterConfigurationService).findById("faas-filter");

        mockMvc.perform(get("/api/filters/{id}", "faas-filter"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/filters/faas-filter"))

                .andExpect(jsonPath("$.id", is("faas-filter")))
                .andExpect(jsonPath("$.technologyType", is("FAAS")))
                .andExpect(jsonPath("$.filters.length()", is(49)));
    }

    @Test
    @DisplayName("GET /api/filters/{id} - Not Found")
    void testGetFilterConfigurationByIdNotFound() throws Exception {
        doReturn(Optional.empty()).when(filterConfigurationService).findById("fid-1");
        mockMvc.perform(get("/api/filters/{id}", "fid-1"))
                .andExpect(status().isNotFound());
    }
}
