package com.asix.demo.authentication.repository;

import com.asix.demo.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    @Query("SELECT s FROM User s WHERE s.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("SELECT s FROM User s WHERE s.username = ?1")
    Optional<User> checkCreatedBy(String username);
}
