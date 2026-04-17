package com.augmind.app.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnStats() throws Exception {
        mockMvc.perform(get("/stats"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.tasksDone").exists())
            .andExpect(jsonPath("$.sessionsCompleted").exists())
            .andExpect(jsonPath("$.studyHours").exists())
            .andExpect(jsonPath("$.streak").exists());
    }

    @Test
    void shouldReturnSubjects() throws Exception {
        mockMvc.perform(get("/subjects"))
            .andExpect(status().isOk());
    }
}
