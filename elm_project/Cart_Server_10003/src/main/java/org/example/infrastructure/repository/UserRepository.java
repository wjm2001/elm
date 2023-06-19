package org.example.infrastructure.repository;

import org.example.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserById(Long userId);
}
