package com.asix.demo.authentication.repository;

import com.asix.demo.authentication.model.Role;
import com.asix.demo.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT s FROM Role s WHERE s.roleName = ?1")
    Optional<Role> findByRoleName(String roleName);
}
