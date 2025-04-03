package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.DosageLogDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import java.time.Instant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DosageLogControllerTest extends IntegrationSpec {

    @Test
    @Order(1)
    void createDosageLog() throws Exception {
        DosageLogDto input = DosageLogDto.builder()
                .dosageGiven(5L)
                .administeredAt(Instant.parse("2025-03-30T10:00:00Z"))
                .notes("First dosage log entry")
                .build();

        mockMvc.perform(post("/dosage-logs/{prescriptionId}", 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dosageGiven").value(5))
                .andExpect(jsonPath("$.notes").value("First dosage log entry"))
                .andReturn();
    }

    @Test
    @Order(2)
    void updateDosageLog() throws Exception {
        DosageLogDto updated = DosageLogDto.builder()
                .dosageGiven(7L)
                .administeredAt(Instant.parse("2025-03-30T12:00:00Z"))
                .notes("Updated dosage")
                .build();

        mockMvc.perform(put("/dosage-logs/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updated))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dosageGiven").value(7))
                .andExpect(jsonPath("$.notes").value("Updated dosage"));
    }

    @Test
    @Order(3)
    void getDosageLogById() throws Exception {
        mockMvc.perform(get("/dosage-logs/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dosageGiven").value(7))
                .andExpect(jsonPath("$.notes").value("Updated dosage"));
    }

    @Test
    @Order(4)
    void deleteDosageLog() throws Exception {
        mockMvc.perform(delete("/dosage-logs/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
