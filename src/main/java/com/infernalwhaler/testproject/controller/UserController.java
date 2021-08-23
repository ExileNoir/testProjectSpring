package com.infernalwhaler.testproject.controller;

import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.service.ICrudService;
import com.infernalwhaler.testproject.service.IUserService;
import com.infernalwhaler.testproject.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

/**
 * Responsible for processing incoming REST API requests,
 * preparing a model and returning the view to be rendered as response
 *
 * @author sDeseure
 * @project TestProject
 * @date 17/08/2021
 * @see ICrudService
 * @see IUserService
 * @see UserServiceImpl
 * @see User
 */

@RequestMapping("/user")
@RestController
public class UserController {

    private final IUserService userService;

    public UserController(final IUserService userService) {
        this.userService = userService;
    }


    /**
     * Shows all registered users
     *
     * @return Set of registered users
     * @see ICrudService#findAll()
     * @see UserServiceImpl#findAll()
     */
    @GetMapping("/findUsers")
    public Set<User> findUsers() {
        return userService.findAll();
    }

    /**
     * Finds a user by userId
     *
     * @param userId of the user
     * @return User
     * @see com.infernalwhaler.testproject.service.ICrudService#findById(Object)
     * @see com.infernalwhaler.testproject.service.UserServiceImpl#findById(Long)
     */
    @GetMapping("{userId}")
    public User findUserById(@PathVariable("userId") final Long userId) {
        return userService.findById(userId);
    }


    /**
     * Registers a user
     *
     * @param user who is an adult French resident
     * @return User
     * @see ICrudService#save(Object)
     * @see UserServiceImpl#save(User)
     * @see com.infernalwhaler.testproject.utilities.Adult
     * @see com.infernalwhaler.testproject.utilities.AdultValidator
     * @see com.infernalwhaler.testproject.utilities.CountryFrance
     * @see com.infernalwhaler.testproject.utilities.CountryFranceValidator
     */
    @PostMapping
    @Transactional
    public User processRegistration(@Valid @RequestBody final User user) {
        return userService.save(user);
    }


    /**
     * Deletes a user by userId
     *
     * @param userId of the user
     * @see ICrudService#deleteById(Object)
     * @see UserServiceImpl#deleteById(Long)
     */
    @DeleteMapping({"{userId}"})
    public void deleteUser(@PathVariable("userId") final Long userId) {
        userService.deleteById(userId);
    }
}
