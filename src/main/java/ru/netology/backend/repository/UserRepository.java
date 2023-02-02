package ru.netology.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.backend.entity.User;

import java.util.Optional;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \*
 * \* ----- group JD-16 -----
 * \*
 * \
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
