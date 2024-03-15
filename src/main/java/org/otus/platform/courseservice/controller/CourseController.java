package org.otus.platform.courseservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.courseservice.dto.course.*;
import org.otus.platform.courseservice.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course")
@Validated
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/{id}")
    ResponseEntity<CourseDto> getCourseById(@NotNull @PathVariable UUID id) {
        var response = courseService.getCourseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/{userId}")
    ResponseEntity<CourseListDto> getCoursesByUser(@NotNull @PathVariable UUID id) {
        var response = courseService.getCoursesByUser(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{courseId}")
    ResponseEntity<CourseUserListDto> getUsersByCourse(@NotNull @PathVariable UUID id) {
        var response = courseService.getUsersByCourse(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CreateCourseRequest request) {
        var response = courseService.createCourse(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    ResponseEntity<CourseDto> updateCourse(@Valid @RequestBody UpdateCourseRequest request) {
        var response = courseService.updateCourse(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCourse(@NotNull @PathVariable UUID id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
