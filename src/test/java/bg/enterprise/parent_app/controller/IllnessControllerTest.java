package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.CreateDtoFactory;
import bg.enterprise.parent_app.model.dto.IllnessDto;
import bg.enterprise.parent_app.model.mapper.IllnessMapper;
import bg.enterprise.parent_app.model.search_criteria.EventSearchCriteria;
import bg.enterprise.parent_app.model.type.IllnessType;
import bg.enterprise.parent_app.service.search.SearchableIllnessService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(scripts = {"file:src/test/java/resources/scripts/init.sql"},
        config = @SqlConfig(encoding = "UTF-8", transactionMode = SqlConfig.TransactionMode.ISOLATED),
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
class IllnessControllerTest extends IntegrationSpec {

    @Autowired
    IllnessMapper illnessMapper;

    @Autowired
    SearchableIllnessService searchableIllnessService;

    private static Long testedId;

    @Test
    @Order(1)
    void createIllness() throws Exception {
        String name = "testIllness";
        IllnessType type = IllnessType.VIRAL;
        String description = "testIllnessDescription";
        Long childId = 1L;

        IllnessDto input = CreateDtoFactory.createIllnessDto(null, childId, name, type, description);

        MvcResult result = mockMvc.perform(post("/illness/create")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        IllnessDto createdIllness = objectMapper.readValue(jsonResponse, IllnessDto.class);
        testedId = createdIllness.getId();
    }

    @Test
    @Order(2)
    void updateIllness() throws Exception {
        String name = "testIllness";
        IllnessType type = IllnessType.VIRAL;
        String description = "newDescription";
        Long id = 1L;
        Long childId = 1L;

        IllnessDto input = CreateDtoFactory.createIllnessDto(id, childId, name, type, description);

        mockMvc.perform(patch("/illness/update")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    void searchIllness() throws Exception {
        String name = "testIllness";
        IllnessType type = IllnessType.VIRAL;
        String description = "newDescription";

        EventSearchCriteria criteria = EventSearchCriteria.builder()
                .name(name)
                .illnessType(type)
                .description(description)
                .build();

        mockMvc.perform(post("/illness/search")
                        .content(objectMapper.writeValueAsString(criteria))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].description").value(description))
                .andExpect(jsonPath("$[0].id").value(testedId))
                .andReturn();
    }

    @Test
    @Order(4)
    void deleteIllness() throws Exception {

        mockMvc.perform(delete("/illness/delete/{id}", testedId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}