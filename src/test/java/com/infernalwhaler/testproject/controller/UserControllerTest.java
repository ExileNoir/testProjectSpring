package com.infernalwhaler.testproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.service.IUserService;
import org.assertj.core.util.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 */
//@SpringBootTest
@WebMvcTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @Test
    public void shouldGetUserByIdWhenValidRequest() throws Exception {
        final User user = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(1983, 11, 05), "France");
        when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/user/userId/1")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Doom"))
                .andExpect(jsonPath("$.lastName").value("MC"))
                .andDo(print())
                .andReturn().getResponse();
        ;
    }

    @Test
    public void ShouldRegisterUserWhenValidRequest() throws Exception {
        final User user = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(1983, 11, 05), "France");

        when(userService.save(any(User.class))).thenReturn(user);

        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);

        final String jsonEntity = objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(user);

        mockMvc.perform(post("/user").contentType(APPLICATION_JSON)
                        .content(jsonEntity)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(header().string("Location", "/user/1"))
                .andDo(print())
                .andReturn().getResponse();
    }


    @Test
    public void findAll() throws Exception {
        final User user = new User(1L, "Doom", "MC", "McDOOM", "deathFantastic4", "Doom", LocalDate.of(1983, 11, 05), "France");
        final User user1 = new User(2L, "Tony", "Stark", "stark@avengers.com", "Bubbles", "IronMan", LocalDate.of(1983, 11, 05), "France");
        final User user2 = new User(3L, "John", "Constantine", "Constantine@vertigo.com", "familyMan", "Hellblazer", LocalDate.of(1983, 11, 05), "France");
        when(userService.findAll()).thenReturn(Sets.newLinkedHashSet(user, user1, user2));

        mockMvc.perform(get("/user/findUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[1].username", is("IronMan")))
                .andDo(print())
                .andReturn().getResponse();
        ;
    }
}
