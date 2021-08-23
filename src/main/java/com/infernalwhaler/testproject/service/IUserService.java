package com.infernalwhaler.testproject.service;

import com.infernalwhaler.testproject.model.User;

/**
 * UserService Interface
 *
 * @author sDeseure
 * @project TestProject
 * @date 18/08/2021
 * @see ICrudService
 */


public interface IUserService extends ICrudService<User, Long> {

    /**
     * Finds a user by Email
     *
     * @param email of the user
     * @return user
     */
    User findByEmail(final String email);

    /**
     * Finds a user by username
     *
     * @param username of the user
     * @return user
     */
    User findByUsername(final String username);

    /**
     * Finds a user by lastname
     *
     * @param lastName of the user
     * @return user
     */
    User findByLastName(final String lastName);

    /**
     * Finds a user by lastname && firstname
     *
     * @param lastName  of the user
     * @param firstName of the user
     * @return user
     */
    User findByLastNameAndFirstName(final String lastName, final String firstName);

}
