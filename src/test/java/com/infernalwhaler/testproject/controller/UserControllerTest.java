package com.infernalwhaler.testproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infernalwhaler.testproject.TestProjectApplication;
import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.repository.UserRepository;
import com.infernalwhaler.testproject.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


/**
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 */
//@SpringBootTest
@WebMvcTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;


    @Test
    public void should_GetAccount_When_ValidRequest() throws Exception {
        /* setup mock */
        final User user = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(2020, 11, 05), "France");
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/account/12345")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.accountId").value(1L));

    }

    @Test
    void createAccountWhenValidRequest() throws Exception {
        final User user1 = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(2020, 11, 05), "France");

        when(userService.save(any(User.class))).thenReturn(user1);

      final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);

        final User user = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(2020, 11, 05), "France");
        final String jsonEntity = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);

        mockMvc.perform(post("/user") .contentType(APPLICATION_JSON)
                        .content(jsonEntity)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON));
//                .andExpect(header().string("Location", "/api/account/12345"))
//                .andExpect(jsonPath("$.accountId").value("12345"))
//                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
//                .andExpect(jsonPath("$.balance").value(5000));
    }
//
//    @Test
//    void processRegistration() throws Exception {
//        final String jsonEntity = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);
//
//        mockMvc.perform(post("/user").accept(APPLICATION_JSON)
//                        .content(jsonEntity)
//                        .contentType(APPLICATION_JSON))
//                .andDo(print())
//                .andReturn().getResponse();
//    }


//    @Test
//    void findAll() throws Exception {
//        mockMvc.perform(get("/user/findUsers").accept(APPLICATION_JSON))
//                .andExpect(status().isOk())
//    }
}
