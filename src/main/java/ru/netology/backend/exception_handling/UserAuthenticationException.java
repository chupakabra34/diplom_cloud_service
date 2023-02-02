package ru.netology.backend.exception_handling;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

public class UserAuthenticationException extends RuntimeException{

    public UserAuthenticationException(String message) {
        super(message);
    }
}
