package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.MedicationDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MedicationControllerTest extends IntegrationSpec {

    @Test
    @Order(1)
    void createMedication() throws Exception {
        MedicationDto input = MedicationDto.builder()
                .name("Ibuprofen")
                .brand("PharmaCo")
                .description("Pain reliever and anti-inflammatory")
                .dosageForm("Tablet")
                .openDate(LocalDate.of(2025, 3, 30))
                .instructions("Take with food")
                .build();

        mockMvc.perform(post("/medications")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ibuprofen"))
                .andExpect(jsonPath("$.dosageForm").value("Tablet"))
                .andExpect(jsonPath("$.instructions").value("Take with food"));
    }

    @Test
    @Order(2)
    void updateMedication() throws Exception {
        MedicationDto update = MedicationDto.builder()
                .name("Ibuprofen - Updated")
                .brand("PharmaMax")
                .description("Updated pain reliever description")
                .dosageForm("Capsule")
                .openDate(LocalDate.of(2025, 3, 31))
                .instructions("Updated instructions")
                .build();

        mockMvc.perform(put("/medications/{id}", 2L)
                        .content(objectMapper.writeValueAsString(update))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ibuprofen - Updated"))
                .andExpect(jsonPath("$.dosageForm").value("Capsule"))
                .andExpect(jsonPath("$.instructions").value("Updated instructions"));
    }

    @Test
    @Order(3)
    void getMedicationById() throws Exception {
        mockMvc.perform(get("/medications/{id}", 2L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ibuprofen - Updated"));
    }

    @Test
    @Order(4)
    void getAllMedications() throws Exception {
        mockMvc.perform(get("/medications"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].name").value("Ibuprofen - Updated"));
    }

    @Test
    @Order(5)
    void deleteMedication() throws Exception {
        mockMvc.perform(delete("/medications/{id}", 2L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}