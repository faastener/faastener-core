package org.faastener.core.controllers;

import org.faastener.core.services.ClassificationFrameworkServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ClassificationFrameworkController.class)
@TestPropertySource(properties = "mongock.enabled=false")
class ClassificationFrameworkControllerTest {
    @MockBean
    private ClassificationFrameworkServiceImpl frameworkService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /frameworks/frameworkId - Found")
    void testGetReviewByIdFound() throws Exception {
        // Setup our mocked service
        //ClassificationFramework mockFramework = new ClassificationFramework();

    }
}