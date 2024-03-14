package org.otus.platform.courseservice.service;

import org.otus.platform.courseservice.dto.schedule.CourseScheduleCreateRequest;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleDto;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleListDto;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleUpdateRequest;

import java.util.UUID;

public interface CourseScheduleService {
    CourseScheduleListDto getCourseScheduleListByCourse(UUID id);
    CourseScheduleDto getCourseScheduleById(UUID id);
    CourseScheduleDto createCourseSchedule(CourseScheduleCreateRequest request);
    CourseScheduleDto updateCourseSchedule(CourseScheduleUpdateRequest request);
    void deleteScheduleById(UUID id);
}
