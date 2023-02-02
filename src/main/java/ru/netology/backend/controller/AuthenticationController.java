package ru.netology.backend.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.backend.dto.LoginDto;
import ru.netology.backend.exception_handling.UserAuthenticationException;
import ru.netology.backend.security.JWTUtil;

import java.util.Collections;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

@RestController
public class AuthenticationController {

    private final JWTUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginDto body) {
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getLogin(), body.getPassword());

            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getLogin());

            return Collections.singletonMap("auth-token", token);
        } catch (AuthenticationException authenticationException) {
            throw new UserAuthenticationException("Bad credentials" + authenticationException);
        }
    }
}