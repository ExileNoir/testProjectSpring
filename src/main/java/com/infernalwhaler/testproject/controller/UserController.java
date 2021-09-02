package com.infernalwhaler.testproject.controller;

import com.infernalwhaler.testproject.exceptions.AccountNotFoundException;
import com.infernalwhaler.testproject.model.User;
import com.infernalwhaler.testproject.service.IUserService;
import com.infernalwhaler.testproject.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Set;

/**
 * Responsible for processing incoming REST API requests,
 * preparing a model and returning the view to be rendered as response
 *
 * @author sDeseure
 * @project TestProject
 * @date 17/08/2021
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
     * Registers a user
     *
     * @param user who is an adult French resident
     * @return User
     * @see UserServiceImpl#save(User)
     * @see com.infernalwhaler.testproject.utilities.Adult
     * @see com.infernalwhaler.testproject.utilities.AdultValidator
     * @see com.infernalwhaler.testproject.utilities.CountryFrance
     * @see com.infernalwhaler.testproject.utilities.CountryFranceValidator
     */
    @PostMapping
    public User registerUser(@Valid @RequestBody final User user,
                             final HttpServletResponse httpResponse,
                             final WebRequest request) {
        userService.save(user);

        httpResponse.setStatus(HttpStatus.CREATED.value());
        httpResponse.setHeader("Location", String.format("%s/user/%s", request.getContextPath(), user.getId()));

        return user;
    }


    /**
     * Shows all registered users
     *
     * @return Set of registered users
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
     * @see com.infernalwhaler.testproject.service.UserServiceImpl#findById(Long)
     */
    @GetMapping("/userId/{userId}")
    public User findUserById(@PathVariable("userId") final Long userId,
                             final HttpServletResponse httpResponse,
                             final WebRequest request) {
        final User user = userService.findById(userId);

        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setHeader("Location", String.format("%s/user/userId/%s", request.getContextPath(), userId));

        return user;
    }

    /**
     * Finds a user by username
     *
     * @param username of the user
     * @return User
     * @see com.infernalwhaler.testproject.service.UserServiceImpl#findByUsername(String)
     */
    @GetMapping("/username/{username}")
    public User findByUsername(@PathVariable("username") final String username,
                               final HttpServletResponse httpResponse,
                               final WebRequest request) {
        final User user = userService.findByUsername(username);
        if (ObjectUtils.isEmpty(user)) {
            throw new AccountNotFoundException(String.format("No user with username: %s", username));
        }

        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setHeader("Location", String.format("%s/user/username/%s", request.getContextPath(), username));

        return user;
    }


    /**
     * Deletes a user by userId
     *
     * @param userId of the user
     * @return User
     * @see UserServiceImpl#deleteById(Long)
     */
    @DeleteMapping({"{userId}"})
    public User deleteUser(@PathVariable("userId") final Long userId,
                           final HttpServletResponse httpResponse,
                           final WebRequest request) {
        final User user = userService.findById(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new AccountNotFoundException(String.format("No user with id %s", userId));
        }
        userService.deleteById(userId);

        httpResponse.setStatus(HttpStatus.ACCEPTED.value());
        httpResponse.setHeader("Location", String.format("%s/user/%s", request.getContextPath(), userId));

        return user;
    }
}
