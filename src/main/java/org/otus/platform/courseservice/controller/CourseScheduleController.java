package org.otus.platform.courseservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleCreateRequest;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleDto;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleListDto;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleUpdateRequest;
import org.otus.platform.courseservice.service.CourseScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course/schedule")
@Validated
@RequiredArgsConstructor
public class CourseScheduleController {

    private final CourseScheduleService courseScheduleService;

    @GetMapping("/list/{courseId}")
    ResponseEntity<CourseScheduleListDto> getCourseScheduleListByCourse(@NotNull @PathVariable("courseId") UUID id) {
        var response = courseScheduleService.getCourseScheduleListByCourse(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<CourseScheduleDto> getCourseScheduleById(@NotNull @PathVariable UUID id) {
        var response = courseScheduleService.getCourseScheduleById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    ResponseEntity<CourseScheduleDto> createCourseSchedule(@Valid @RequestBody CourseScheduleCreateRequest request) {
        var response = courseScheduleService.createCourseSchedule(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    ResponseEntity<CourseScheduleDto> updateCourseSchedule(@Valid @RequestBody CourseScheduleUpdateRequest request) {
        var response = courseScheduleService.updateCourseSchedule(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteScheduleById(@NotNull @PathVariable UUID id) {
        courseScheduleService.deleteScheduleById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
