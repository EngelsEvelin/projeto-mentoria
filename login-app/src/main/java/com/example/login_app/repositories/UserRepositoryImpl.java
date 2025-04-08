package com.example.login_app.repositories;

import com.example.login_app.domain.user.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Primary
@Repository
public interface UserRepositoryImpl extends UserRepository, JpaRepository<User, String> {
    @Override
    Optional<Object> findByEmail(String email);
}

