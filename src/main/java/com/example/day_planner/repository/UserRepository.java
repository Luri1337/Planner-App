package com.example.day_planner.repository;

import com.example.day_planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // Метод для поиска по email
}
