package com.examle.eventlocator.repository;

import com.examle.eventlocator.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID>{

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    UUID id(UUID id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
