package com.infernalwhaler.testproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infernalwhaler.testproject.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(2020, 11, 05), "France");
//        user = new User(8L, "Obi-one", "Kenobi", "kenobi@jedi.gal", "SithKiller", "Kenobi", LocalDate.of(2018, 11, 05), "France");

        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    @Test
    void processRegistration() throws Exception {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);

        final String jsonEntity = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);

        mockMvc.perform(post("/user").accept(APPLICATION_JSON)
                        .content(jsonEntity)
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResponse();
    }


    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/user/findUsers").accept(APPLICATION_JSON))
                .andDo(print())
                .andReturn()
                .getResponse();
    }
}
