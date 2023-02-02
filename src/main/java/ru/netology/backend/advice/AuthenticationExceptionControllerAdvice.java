package ru.netology.backend.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.backend.exception_handling.UserAuthenticationException;
import ru.netology.backend.exception_handling.UserAuthenticationExceptionData;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

@RestControllerAdvice
public class AuthenticationExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<UserAuthenticationExceptionData> handleAuthenticationException(UserAuthenticationException e) {

        UserAuthenticationExceptionData data = new UserAuthenticationExceptionData();
        data.setInfo(e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
