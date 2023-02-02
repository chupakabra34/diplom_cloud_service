package ru.netology.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {
    private String login;
    private String password;
}