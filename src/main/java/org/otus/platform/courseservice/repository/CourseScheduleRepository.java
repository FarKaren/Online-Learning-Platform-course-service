package org.otus.platform.courseservice.repository;

import org.otus.platform.courseservice.model.schedule.CourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule, UUID> {
    List<CourseSchedule> findAllByCourseId(UUID courseId);
    Optional<CourseSchedule> findByIdAndDeletedHashIsNull(UUID id);
}
