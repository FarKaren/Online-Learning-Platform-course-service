package org.otus.platform.courseservice.repository;

import org.otus.platform.courseservice.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByIdAndDeletedHashIsNull(UUID id);
}
