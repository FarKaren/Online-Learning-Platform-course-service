package org.otus.platform.courseservice.mapper;

import org.otus.platform.courseservice.dto.course.CourseDto;
import org.otus.platform.courseservice.dto.course.CreateCourseRequest;
import org.otus.platform.courseservice.dto.UserDto;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseMapper {

    public CourseDto toCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .price(course.getPrice())
                .added(course.getAdded())
                .startAt(course.getStartAt())
                .endAt(course.getEndAt())
                .build();
    }

    public Course toCourse(CreateCourseRequest request) {
        return Course.builder()
                .title(request.title())
                .price(request.price())
                .startAt(request.startAt())
                .endAt(request.endAt())
                .build();
    }

    public List<CourseDto> toCourseDtoList(List<Course> courses) {
        return courses.stream().map(this::toCourseDto).toList();
    }

    public List<UserDto> toUserDtoList(List<User> users) {
        return users.stream().map(this::toUserDto).toList();
    }

    public UserDto toUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
