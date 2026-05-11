package com.augmind.app.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSmokeTest {

    @Autowired
    private MockMvc mockMvc;

    /** Build a session that has already passed the access gate. */
    private MockHttpSession grantedSession() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(AccessController.SESSION_ACCESS_GRANTED, Boolean.TRUE);
        return session;
    }

    @Test
    void shouldReturnStats() throws Exception {
        mockMvc.perform(get("/stats").session(grantedSession()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.tasksDone").exists())
            .andExpect(jsonPath("$.sessionsCompleted").exists())
            .andExpect(jsonPath("$.studyHours").exists())
            .andExpect(jsonPath("$.streak").exists());
    }

    @Test
    void shouldReturnSubjects() throws Exception {
        mockMvc.perform(get("/subjects").session(grantedSession()))
            .andExpect(status().isOk());
    }

    @Test
    void shouldReturn401ForUnauthenticatedStatsRequest() throws Exception {
        mockMvc.perform(get("/stats"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401ForUnauthenticatedSubjectsRequest() throws Exception {
        mockMvc.perform(get("/subjects"))
            .andExpect(status().isUnauthorized());
    }
}
