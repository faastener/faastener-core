package org.faastener.core.controllers;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.services.ClassificationFrameworkService;
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
@WebMvcTest(ClassificationFrameworkController.class)
@TestPropertySource(properties = "mongock.enabled=false")
class ClassificationFrameworkControllerTest {
    @MockBean
    private ClassificationFrameworkService frameworkService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/v1/frameworks/{id} - Found")
    void testGetFrameworkByIdFound() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ClassificationFramework mockFramework = mapper.readValue(new ClassPathResource("data/cu1-frameworks.json").getFile(), ClassificationFramework.class);
        doReturn(Optional.of(mockFramework)).when(frameworkService).findById("fw-faas");

        mockMvc.perform(get("/api/v1/frameworks/{id}", "fw-faas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(header().string(HttpHeaders.ETAG, "\"1.0\""))
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/frameworks/fw-faas"))

                // Validate the returned fields
                .andExpect(jsonPath("$.id", is("fw-faas")))
                .andExpect(jsonPath("$.technologyType", is("FAAS")))
                .andExpect(jsonPath("$.frameworkViews.length()", is(2)))
                .andExpect(jsonPath("$.frameworkViews[0].id", is("view:faas:managerial")));
    }

    @Test
    @DisplayName("GET /api/v1/frameworks/{id} - Not Found")
    void testGetFrameworkByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(frameworkService).findById("fw-faas");

        // Execute the GET request
        mockMvc.perform(get("/api/v1/frameworks/{id}", "fw-faas"))

                // Validate that we get a 404 Not Found response
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/v1/frameworks - Success")
    void testCreateFramework() throws Exception {

    }





}