package com.glitchhunters.portalempresa.repository;

import com.glitchhunters.portalempresa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository incluye save(), findById(), findAll() y deleteById()
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
