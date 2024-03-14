package org.otus.platform.courseservice.repository;

import org.otus.platform.courseservice.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByIdAndDeletedHashIsNull(UUID id);
}
