package org.faastener.core.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search-queries")
public class SearchQueryController {

    @PostMapping
    public ResponseEntity<String> generateQuery(@RequestBody String factSource) {
        System.out.println(factSource);

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{ \"url\": \"http://localhost:4300/technologies\"}");
    }
}
