package com.infernalwhaler.testproject.repository;

import com.infernalwhaler.testproject.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * A repository to manage {@link User} instances
 *
 * @author sDeseure
 * @project TestProject
 * @date 17/08/2021
 * @see CrudRepository
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by username
     *
     * @param username of the user
     * @return user
     */
    User findByUsername(final String username);

}
