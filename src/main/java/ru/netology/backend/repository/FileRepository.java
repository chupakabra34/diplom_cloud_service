package ru.netology.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.backend.entity.File;
import ru.netology.backend.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

public interface FileRepository extends JpaRepository<File, String> {
    void deleteByName(String name);

    Optional<File> findByNameAndUser(String name, User user);

    List<File> findAllByUser(User user);
}
