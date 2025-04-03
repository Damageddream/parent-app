package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.mapper.ChildMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChildControllerTest extends IntegrationSpec {

    @Autowired
    ChildMapper childMapper;


    @Test
    @Order(1)
    void createChild() throws Exception {
        Long parentId = testParentId;
        String firstName = "Test";
        String lastName = "Child";
        LocalDate birthdate = LocalDate.of(2020, 5, 15);
        String notes = "Healthy";

        ChildDto input = ChildDto.builder()
                .parentId(parentId)
                .firstName(firstName)
                .lastName(lastName)
                .birthdate(birthdate)
                .notes(notes)
                .prescriptions(Collections.emptyList())
                .notifications(Collections.emptyList())
                .build();

        mockMvc.perform(post("/child")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.birthdate").value(birthdate.toString()))
                .andExpect(jsonPath("$.notes").value(notes))
                .andExpect(jsonPath("$.parentId").value(parentId))
                .andReturn();

    }

    @Test
    @Order(2)
    void updateChild() throws Exception {
        Long parentId = testParentId;
        String firstName = "Updated";
        String lastName = "Child";
        LocalDate birthdate = LocalDate.of(2020, 5, 15);
        String notes = "Updated notes";

        ChildDto input = ChildDto.builder()
                .parentId(parentId)
                .firstName(firstName)
                .lastName(lastName)
                .birthdate(birthdate)
                .notes(notes)
                .prescriptions(Collections.emptyList())
                .notifications(Collections.emptyList())
                .build();

        mockMvc.perform(put("/child/{id}", 2L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.notes").value(notes))
                .andReturn();
    }

    @Test
    @Order(3)
    void getChildById() throws Exception {
        mockMvc.perform(get("/child/{id}", 2L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andReturn();
    }

    @Test
    @Order(4)
    void deleteChild() throws Exception {
        mockMvc.perform(delete("/child/{id}", 2L))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}
