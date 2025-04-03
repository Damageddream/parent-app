package bg.enterprise.parent_app.controller;

import bg.enterprise.parent_app.model.dto.RegisterRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegistrationAndLoginControllerTest extends IntegrationSpec {

    @Test
    @Order(1)
    void registerUser() throws Exception {
        RegisterRequest input = new RegisterRequest();
        input.setUsername("testuser");
        input.setEmail("testuser@example.com");
        input.setPassword("password123");

        mockMvc.perform(post("/register")
                        .content(objectMapper.writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();
    }

    @Test
    @Order(2)
    void loginUser() throws Exception {
        LoginController.LoginRequest loginInput = new LoginController.LoginRequest();
        loginInput.setUsername("testuser");
        loginInput.setPassword("password123");

        mockMvc.perform(post("/login")
                        .content(objectMapper.writeValueAsString(loginInput))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();
    }
}