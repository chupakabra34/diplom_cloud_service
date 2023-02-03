package ru.netology.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.netology.backend.security.JWTFilter;
import ru.netology.backend.security.JWTUtil;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

@SpringBootTest(classes = {AuthenticationController.class, JWTUtil.class})
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JWTUtil jwtUtil;
    @MockBean
    private JWTFilter jwtFilter;
    @MockBean
    private AuthenticationManager authManager;

    @Test
    @WithMockUser(username = "user", password = "user")
    void loginHandler() throws Exception {
        String token = jwtUtil.generateToken("user");
        assertNotNull(token);
        mockMvc.perform(MockMvcRequestBuilders.get("/list")
                .header("auth-token", token)).andExpect(status().isOk());
    }
}