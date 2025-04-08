package com.example.login_app.repositories;

import com.example.login_app.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<Object> findByEmail(String username);
}
