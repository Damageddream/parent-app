package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.ParentDto;
import bg.enterprise.parent_app.model.mapper.ParentMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParentControllerTest extends IntegrationSpec {

    @Autowired
    ParentMapper parentMapper;

    @Test
    @Order(1)
    void createParent() throws Exception {
        ParentDto input = ParentDto.builder()
                .firstName("Test")
                .lastName("Parent")
                .email("test.parent@example.com")
                .password("password123")
                .phone("1234567890")
                .children(new ArrayList<>())
                .dosageLogs(new ArrayList<>())
                .notifications(new ArrayList<>())
                .build();

        mockMvc.perform(post("/parent")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("Parent"))
                .andExpect(jsonPath("$.email").value("test.parent@example.com"))
                .andReturn();
    }

    @Test
    @Order(2)
    void updateParent() throws Exception {
        ParentDto input = ParentDto.builder()
                .firstName("Updated")
                .lastName("Parent")
                .password("newpassword456")
                .phone("9876543210")
                .children(new ArrayList<>())
                .dosageLogs(new ArrayList<>())
                .notifications(new ArrayList<>())
                .build();

        mockMvc.perform(put("/parent/{id}", 2L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.phone").value("9876543210"))
                .andReturn();
    }

    @Test
    @Order(3)
    void getParentById() throws Exception {
        mockMvc.perform(get("/parent/{id}", 2L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.phone").value("9876543210"))
                .andReturn();
    }

    @Test
    @Order(4)
    void deleteParent() throws Exception {
        mockMvc.perform(delete("/parent/{id}", 2L))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }
}

