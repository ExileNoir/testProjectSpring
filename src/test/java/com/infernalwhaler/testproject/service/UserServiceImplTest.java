package com.infernalwhaler.testproject.service;

import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 */

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {


    public static final String LAST_NAME = "Doom";
    public static final String PWD = "Fantastic4";

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl service;

    User returnUser;

    @BeforeEach
    void setUp() {
        returnUser = User.builder().id(1L).lastName(LAST_NAME).password(PWD).build();
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        returnUser.setPassword(encoder.encode(returnUser.getPassword()));
    }

    @Test
    void findByLastName() {
        when(userRepository.findByLastName(any())).thenReturn(returnUser);
        final User doom = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, doom.getLastName());
        verify(userRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        final Set<User> returnUsers = new HashSet<>();
        returnUsers.add(User.builder().id(1L).build());
        returnUsers.add(User.builder().id(2L).build());

        when(userRepository.findAll()).thenReturn(returnUsers);

        final Set<User> users = service.findAll();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(returnUser));
        final User user = service.findById(1L);
        assertNotNull(user);
    }

    @Test
    void save() {
        final User userToSave = User.builder().id(1L).password("Password").build();
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userToSave.setPassword(encoder.encode(userToSave.getPassword()));

        when(userRepository.save(any())).thenReturn(returnUser);

        final User savedUser = service.save(userToSave);

        assertNotNull(savedUser);
        verify(userRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnUser);
        verify(userRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(userRepository).deleteById(anyLong());
    }
}