package org.otus.platform.courseservice.mapper;

import org.otus.platform.courseservice.dto.schedule.CourseScheduleCreateRequest;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleDto;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.schedule.CourseSchedule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseScheduleMapper {

    public List<CourseScheduleDto> toCourseScheduleList(List<CourseSchedule> schedules) {
        return schedules.stream().map(this::toCourseScheduleDto).toList();
    }

    public CourseScheduleDto toCourseScheduleDto(CourseSchedule courseSchedule) {
        return CourseScheduleDto.builder()
                .scheduleId(courseSchedule.getId())
                .courseId(courseSchedule.getCourse().getId())
                .lessonDate(courseSchedule.getLessonDate())
                .courseTitle(courseSchedule.getCourseTitle())
                .lessonStatus(courseSchedule.getLessonStatus())
                .rescheduleToDate(courseSchedule.getRescheduleToDate())
                .build();
    }

    public CourseSchedule toCourseSchedule(CourseScheduleCreateRequest dto, Course course) {
        return CourseSchedule.builder()
                .course(course)
                .courseTitle(course.getTitle())
                .lessonDate(dto.lessonDate())
                .lessonStatus(dto.lessonStatus())
                .build();
    }
}
