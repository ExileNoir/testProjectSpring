package com.infernalwhaler.testproject.service;

import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * class that implements {@link IUserService}
 *
 * @author sDeseure
 * @project TestProject
 * @date 18/08/2021
 * @see IUserService
 * @see UserRepository
 */

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;


    public UserServiceImpl(final UserRepository repo) {
        this.userRepository = repo;
    }


    /**
     * Finds a user by Email
     *
     * @param email of the user
     * @return user
     */
    @Override
    public User findByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds a user by username
     *
     * @param username of the user
     * @return user
     */
    @Override
    public User findByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Finds a user by lastname
     *
     * @param lastName of the user
     * @return user
     */
    @Override
    public User findByLastName(final String lastName) {
        return userRepository.findByLastName(lastName);
    }

    /**
     * Finds a user by lastname && firstname
     *
     * @param lastName  of the user
     * @param firstName of the user
     * @return user
     */
    @Override
    public User findByLastNameAndFirstName(final String lastName, final String firstName) {
        return userRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    /**
     * Deletes a user
     *
     * @param user object
     */
    @Override
    public void delete(final User user) {
        this.userRepository.delete(user);
    }

    /**
     * Deletes a user by userId
     *
     * @param userId of the user
     */
    @Override
    public void deleteById(final Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Finds all users
     *
     * @return Set of users
     */
    @Override
    public Set<User> findAll() {
        final Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * Finds a user by userId
     *
     * @param userId of the user
     * @return user
     */
    @Override
    public User findById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(null);
    }

    /**
     * Saves a user
     *
     * @param user object
     * @return user
     */
    @Override
    public User save(final User user) {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encodePassword = encoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        return userRepository.save(user);
    }
}
