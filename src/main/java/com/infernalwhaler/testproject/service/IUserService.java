package com.infernalwhaler.testproject.service;

import com.infernalwhaler.testproject.model.User;

import java.util.Set;

/**
 * UserService Interface
 *
 * @author sDeseure
 * @project TestProject
 * @date 18/08/2021
 */


public interface IUserService {

    Set<User> findAll();

    User findById(final Long id);

    User save(final User user);

    void delete(final User user);

    void deleteById(final Long id);

    User findByUsername(final String username);
}
