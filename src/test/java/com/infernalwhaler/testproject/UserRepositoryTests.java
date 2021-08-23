package com.infernalwhaler.testproject;

import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;


/**
 * @author sDeseure
 * @project TestProject
 * @date 17/08/2021
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testCreateUser() {
        final User user = new User();

        user.setEmail("Deseure.s@gmail.com");
        user.setPassword("password");
        user.setFirstName("Steven");
        user.setLastName("Deseure");
        user.setUsername("ExileNoir");
        user.setBirthDate(LocalDate.of(1983, 10, 23));
        user.setCountry("France");

        final User savedUser = repo.save(user);
        final User existUser = entityManager.find(User.class, savedUser.getId());

        Assertions.assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void testFindUserByEmail() {
        final String email = "Deseure.s@gmail.com";
        final User user = repo.findByEmail(email);
        Assertions.assertThat(user).isNotNull();
    }


}
