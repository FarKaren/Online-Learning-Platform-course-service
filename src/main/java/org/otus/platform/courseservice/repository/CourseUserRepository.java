package org.otus.platform.courseservice.repository;

import org.otus.platform.courseservice.model.course.CourseUser;
import org.otus.platform.courseservice.model.course.CourseUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseUserRepository extends JpaRepository<CourseUser, CourseUserId> {
    List<CourseUser> findAllByUserId(UUID id);
    List<CourseUser> findAllByCourseId(UUID id);
}
