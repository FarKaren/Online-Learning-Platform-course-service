package org.otus.platform.courseservice.service;

import org.otus.platform.courseservice.dto.course.*;

import java.util.UUID;

public interface CourseService {
    CourseDto getCourseById(UUID id);
    CourseListDto getCoursesByUser(UUID id);
    CourseUserListDto getUsersByCourse(UUID id);
    CourseDto createCourse(CreateCourseRequest request);
    CourseDto updateCourse(UpdateCourseRequest request);
    CourseDto joinToCourse(JoinToCourseRequest request);
    void deleteCourse(UUID id);
}
