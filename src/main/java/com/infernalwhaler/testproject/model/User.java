package com.infernalwhaler.testproject.model;

import com.infernalwhaler.testproject.utilities.Adult;
import com.infernalwhaler.testproject.utilities.CountryFrance;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * A User representation
 *
 * @author sDeseure
 * @project TestProject
 * @date 17/08/2021
 * @see Adult
 * @see com.infernalwhaler.testproject.utilities.AdultValidator
 * @see CountryFrance
 * @see com.infernalwhaler.testproject.utilities.CountryFranceValidator
 */

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Adult()
    private LocalDate birthDate;

    @Column(name = "country")
    @CountryFrance
    private String country;

    @Builder
    public User(Long id, String firstName, String lastName, String email, String password, String username, LocalDate birthDate, String country) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.username = username;
        this.birthDate = birthDate;
        this.country = country;
    }
}
