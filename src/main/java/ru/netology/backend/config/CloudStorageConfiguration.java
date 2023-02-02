package ru.netology.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.netology.backend.repository.UserRepository;
import ru.netology.backend.security.JWTFilter;
import ru.netology.backend.security.JWTUtil;
import ru.netology.backend.security.MyUserDetailsService;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */


@Configuration
public class CloudStorageConfiguration {

    @Bean
    public JWTFilter jwtFilter(UserDetailsService userDetailsService) {
        return new JWTFilter(jwtUtil(userDetailsService));
    }

    @Bean
    public JWTUtil jwtUtil(UserDetailsService userDetailsService) {
        return new JWTUtil(userDetailsService);
    }

    @Bean
    public MyUserDetailsService myUserDetailsService(UserRepository userRepository){
        return new MyUserDetailsService(userRepository);
    }
}