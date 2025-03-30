package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.ChronicConditionDto;
import bg.enterprise.parent_app.model.mapper.ChronicConditionMapper;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"file:src/test/java/resources/scripts/chronicConditionTestInit.sql"},
        config = @SqlConfig(encoding = "UTF-8", transactionMode = SqlConfig.TransactionMode.ISOLATED),
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChronicConditionControllerTest extends IntegrationSpec {

    @Autowired
    ChronicConditionMapper chronicConditionMapper;

    @Test
    @Order(1)
    void createChronicCondition() throws Exception {
        ChronicConditionDto input = ChronicConditionDto.builder()
                .name("Asthma")
                .description("Chronic asthma condition")
                .instructions("Use inhaler when necessary")
                .notes("Monitor during winter")
                .build();

        MvcResult result = mockMvc.perform(post("/chronic-conditions/{childId}", 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Asthma"))
                .andReturn();

    }

    @Test
    @Order(2)
    void updateChronicCondition() throws Exception {
        ChronicConditionDto input = ChronicConditionDto.builder()
                .name("Asthma")
                .description("Updated asthma condition")
                .instructions("New instructions")
                .notes("Updated notes")
                .build();

        mockMvc.perform(put("/chronic-conditions/{id}", 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated asthma condition"))
                .andExpect(jsonPath("$.instructions").value("New instructions"))
                .andExpect(jsonPath("$.notes").value("Updated notes"));
    }

    @Test
    @Order(3)
    void searchChronicCondition() throws Exception {
        EventSearchCriteria criteria = EventSearchCriteria.builder()
                .name("Asthma")
                .description("Updated asthma condition")
                .build();

        mockMvc.perform(post("/chronic-conditions/search")
                        .content(objectMapper.writeValueAsString(criteria))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Asthma"))
                .andExpect(jsonPath("$[0].description").value("Updated asthma condition"));
    }

    @Test
    @Order(4)
    void deleteChronicCondition() throws Exception {
        mockMvc.perform(delete("/chronic-conditions/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
