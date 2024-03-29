package org.otus.platform.courseservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.courseservice.dto.course.*;
import org.otus.platform.courseservice.mapper.CourseMapper;
import org.otus.platform.courseservice.model.course.CourseUser;
import org.otus.platform.courseservice.repository.CourseRepository;
import org.otus.platform.courseservice.repository.CourseUserRepository;
import org.otus.platform.courseservice.repository.UserRepository;
import org.otus.platform.courseservice.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseUserRepository courseUserRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public CourseDto getCourseById(UUID id) {
        log.info("invoke getCourseById() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + id + "not found"));
        return courseMapper.toCourseDto(course);
    }

    @Transactional
    @Override
    public CourseListDto getCoursesByUser(UUID id) {
        log.info("invoke getCoursesByUser() method");
        List<CourseUser> courseUserList = courseUserRepository.findAllByUserId(id);
        var courses = courseUserList.stream().map(CourseUser::getCourse).toList();
        var courseDtoList = courseMapper.toCourseDtoList(courses);
        return new CourseListDto(courseDtoList);
    }

    @Transactional
    @Override
    public CourseUserListDto getUsersByCourse(UUID id) {
        log.info("invoke getUsersByCourse() method");
        List<CourseUser> courseUserList = courseUserRepository.findAllByCourseId(id);
        var users = courseUserList.stream().map(CourseUser::getUser).toList();
        var userDtoList = courseMapper.toUserDtoList(users);
        return new CourseUserListDto(userDtoList);
    }

    @Transactional
    @Override
    public CourseDto createCourse(CreateCourseRequest request) {
        log.info("invoke createCourse() method");
        var course = courseMapper.toCourse(request);
        var savedCourse = courseRepository.save(course);
        return courseMapper.toCourseDto(savedCourse);
    }

    @Transactional
    @Override
    public CourseDto updateCourse(UpdateCourseRequest request) {
        log.info("invoke updateCourse() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.id() + "not found"));
        course.setTitle(request.title());
        course.setPrice(request.price());
        course.setStartAt(request.startAt());
        var updatedCourse = courseRepository.save(course);
        return courseMapper.toCourseDto(updatedCourse);
    }

    @Transactional
    @Override
    public CourseDto joinToCourse(JoinToCourseRequest request) {
        log.info("invoke joinToCourse() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + "not found"));
        var user = userRepository.findByIdAndDeletedHashIsNull(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User with id: " + request.userId() + "not found"));
        var courseUser = CourseUser.builder()
                .user(user)
                .course(course)
                .userType(request.userType())
                .build();
        courseUserRepository.save(courseUser);
        return courseMapper.toCourseDto(course);
    }

    @Transactional
    @Override
    public void deleteCourse(UUID id) {
        log.info("invoke deleteCourse() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + id + "not found"));
        course.setDeletedAt(ZonedDateTime.now());
        course.setDeletedHash(UUID.randomUUID());
        courseRepository.save(course);
    }
}
