package org.otus.platform.courseservice.repository;

import org.otus.platform.courseservice.model.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, UUID> {
    Optional<Homework> findByIdAndDeletedHashIsNull(UUID id);
}
