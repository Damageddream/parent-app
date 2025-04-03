package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.InjuryDto;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import bg.enterprise.parent_app.model.type.InjuryType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InjuryControllerTest extends IntegrationSpec {

    @Test
    @Order(1)
    void createInjury() throws Exception {
        InjuryDto input = InjuryDto.builder()
                .name("Sprained Ankle")
                .description("Swollen ankle after fall")
                .injuryType(InjuryType.SPRAIN)
                .build();

        mockMvc.perform(post("/injuries/{childId}", 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sprained Ankle"))
                .andExpect(jsonPath("$.description").value("Swollen ankle after fall"))
                .andExpect(jsonPath("$.injuryType").value("SPRAIN"));
    }

    @Test
    @Order(2)
    void updateInjury() throws Exception {
        InjuryDto updated = InjuryDto.builder()
                .name("Fractured Arm - Updated")
                .description("Updated details about the fracture")
                .injuryType(InjuryType.FRACTURE)
                .build();

        mockMvc.perform(put("/injuries/{id}", 1L)
                        .content(objectMapper.writeValueAsString(updated))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fractured Arm - Updated"))
                .andExpect(jsonPath("$.description").value("Updated details about the fracture"));
    }

    @Test
    @Order(3)
    void getInjuryById() throws Exception {
        mockMvc.perform(get("/injuries/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fractured Arm - Updated"));
    }

    @Test
    @Order(4)
    void searchInjury() throws Exception {
        EventSearchCriteria criteria = EventSearchCriteria.builder()
                .name("Fractured Arm - Updated")
                .description("Updated details about the fracture")
                .build();

        mockMvc.perform(post("/injuries/search")
                        .content(objectMapper.writeValueAsString(criteria))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Fractured Arm - Updated"))
                .andExpect(jsonPath("$[0].description").value("Updated details about the fracture"));
    }

    @Test
    @Order(5)
    void deleteInjury() throws Exception {
        mockMvc.perform(delete("/injuries/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}