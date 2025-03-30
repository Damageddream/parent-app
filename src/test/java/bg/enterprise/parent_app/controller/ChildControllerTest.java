package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.CreateDtoFactory;
import bg.enterprise.parent_app.model.dto.ChildDto;
import bg.enterprise.parent_app.model.mapper.ChildMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"file:src/test/java/resources/scripts/childTestInit.sql"},
        config = @SqlConfig(encoding = "UTF-8", transactionMode = SqlConfig.TransactionMode.ISOLATED),
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChildControllerTest extends IntegrationSpec {

    @Autowired
    ChildMapper childMapper;


    @Test
    @Order(1)
    void createChild() throws Exception {
        Long parentId = 1L;
        String firstName = "Test";
        String lastName = "Child";
        LocalDate birthdate = LocalDate.of(2020, 5, 15);
        String notes = "Healthy";

        ChildDto input = CreateDtoFactory.createChildDto(parentId, firstName, lastName, birthdate, notes, List.of(), List.of());

        MvcResult result = mockMvc.perform(post("/children")
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
        Long parentId = 1L;
        String firstName = "Updated";
        String lastName = "Child";
        LocalDate birthdate = LocalDate.of(2020, 5, 15);
        String notes = "Updated notes";

        ChildDto input = CreateDtoFactory.createChildDto(parentId, firstName, lastName, birthdate, notes, List.of(), List.of());

        mockMvc.perform(put("/children/{id}",1L)
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
        mockMvc.perform(get("/children/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andReturn();
    }

    @Test
    @Order(4)
    void deleteChild() throws Exception {
        mockMvc.perform(delete("/children/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
    }


}
