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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private String filename;
    private Long size;
}