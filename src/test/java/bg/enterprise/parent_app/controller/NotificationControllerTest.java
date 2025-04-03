package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.NotificationDto;
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
public class NotificationControllerTest extends IntegrationSpec {

    @Test
    @Order(1)
    void createNotification() throws Exception {
        NotificationDto input = NotificationDto.builder()
                .message("Upcoming vaccination due")
                .read(false)
                .build();

        mockMvc.perform(post("/notifications/{parentId}/{childId}", 1L, 1L)
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Upcoming vaccination due"))
                .andExpect(jsonPath("$.read").value(false));
    }

    @Test
    @Order(2)
    void updateNotification() throws Exception {
        NotificationDto update = NotificationDto.builder()
                .message("Vaccination reminder updated")
                .read(true)
                .build();

        mockMvc.perform(put("/notifications/{id}", 1L)
                        .content(objectMapper.writeValueAsString(update))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Vaccination reminder updated"))
                .andExpect(jsonPath("$.read").value(true));
    }

    @Test
    @Order(3)
    void getNotificationById() throws Exception {
        mockMvc.perform(get("/notifications/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Vaccination reminder updated"))
                .andExpect(jsonPath("$.read").value(true));
    }

    @Test
    @Order(4)
    void deleteNotification() throws Exception {
        mockMvc.perform(delete("/notifications/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
