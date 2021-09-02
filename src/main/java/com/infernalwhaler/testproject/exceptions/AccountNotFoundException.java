package com.infernalwhaler.testproject.exceptions;

/**
 * @author sDeseure
 * @project testProjectSpring
 * @date 1/09/2021
 */

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(final String message) {
        super(message);
    }
}
