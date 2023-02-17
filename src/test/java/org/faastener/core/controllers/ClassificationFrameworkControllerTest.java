package org.faastener.core.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.faastener.core.model.ClassificationFramework;
import org.faastener.core.model.CriteriaGrouping;
import org.faastener.core.model.CriterionType;
import org.faastener.core.model.FrameworkView;
import org.faastener.core.model.TechnologyType;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        List<CriterionType> mockCriteria = new ArrayList<>();
        mockCriteria.add(new CriterionType("criterion1", "criterion type 1", "criterion type 1 description", Collections.emptyList()));
        mockCriteria.add(new CriterionType("criterion1", "criterion type 2", "criterion type 2 description", Collections.emptyList()));
        CriteriaGrouping mockGrouping = new CriteriaGrouping("groupingId", "test grouping", "grouping description", Collections.emptyList(), mockCriteria);
        FrameworkView mockView = new FrameworkView("viewId", "test view", "view description", Collections.singletonList(mockGrouping));
        ClassificationFramework mockFramework = new ClassificationFramework("frameworkId", "test", TechnologyType.FAAS, "1.0", "some description", Collections.singletonList(mockView));

        doReturn(mockFramework).when(frameworkService).save(any());

        mockMvc.perform(post("/api/v1/frameworks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(mockFramework)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("frameworkId")))
                .andExpect(jsonPath("$.technologyType", is("FAAS")))
                .andExpect(jsonPath("$.frameworkViews.length()", is(1)))
                .andExpect(jsonPath("$.frameworkViews[0].id", is("viewId")))
                .andExpect(jsonPath("$.frameworkViews[0].criteriaGroupings.length()", is(1)))
                .andExpect(jsonPath("$.frameworkViews[0].criteriaGroupings[0].id", is("groupingId")))
                .andExpect(jsonPath("$.frameworkViews[0].criteriaGroupings[0].criteriaGroupings.length()", is(0)))
                .andExpect(jsonPath("$.frameworkViews[0].criteriaGroupings[0].criteria.length()", is(2)));
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}