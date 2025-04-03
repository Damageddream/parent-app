package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.PrescriptionDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrescriptionControllerTest extends IntegrationSpec {

    @Test
    @Order(1)
    void createPrescription() throws Exception {
        PrescriptionDto input = PrescriptionDto.builder()
                .childId(1L)
                .medicationId(1L)
                .dosageAmount(5.0f)
                .dosageUnit("mg")
                .frequencyPerDay(3)
                .startDate(LocalDate.of(2025, 4, 1))
                .endDate(LocalDate.of(2025, 4, 10))
                .notes("Take with water")
                .dosageLogs(new ArrayList<>())
                .build();

        mockMvc.perform(post("/prescriptions/{childId}", 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.childId").value(1))
                .andExpect(jsonPath("$.medicationId").value(1))
                .andExpect(jsonPath("$.dosageAmount").value(5.0))
                .andExpect(jsonPath("$.dosageUnit").value("mg"))
                .andExpect(jsonPath("$.frequencyPerDay").value(3))
                .andExpect(jsonPath("$.notes").value("Take with water"))
                .andReturn();
    }

    @Test
    @Order(2)
    void updatePrescription() throws Exception {
        PrescriptionDto input = PrescriptionDto.builder()
                .childId(1L)
                .medicationId(1L)
                .dosageAmount(7.5f)
                .dosageUnit("mg")
                .frequencyPerDay(2)
                .startDate(LocalDate.of(2025, 4, 2))
                .endDate(LocalDate.of(2025, 4, 12))
                .notes("Updated dosage and schedule")
                .dosageLogs(new ArrayList<>())
                .build();

        mockMvc.perform(put("/prescriptions/{id}", 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dosageAmount").value(7.5))
                .andExpect(jsonPath("$.frequencyPerDay").value(2))
                .andExpect(jsonPath("$.notes").value("Updated dosage and schedule"))
                .andReturn();
    }

    @Test
    @Order(3)
    void getPrescriptionById() throws Exception {
        mockMvc.perform(get("/prescriptions/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.childId").value(1))
                .andExpect(jsonPath("$.medicationId").value(1))
                .andReturn();
    }

    @Test
    @Order(4)
    void deletePrescription() throws Exception {
        mockMvc.perform(delete("/prescriptions/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}