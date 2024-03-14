package org.otus.platform.courseservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleCreateRequest;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleDto;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleListDto;
import org.otus.platform.courseservice.dto.schedule.CourseScheduleUpdateRequest;
import org.otus.platform.courseservice.mapper.CourseScheduleMapper;
import org.otus.platform.courseservice.repository.CourseRepository;
import org.otus.platform.courseservice.repository.CourseScheduleRepository;
import org.otus.platform.courseservice.service.CourseScheduleService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseScheduleServiceImpl implements CourseScheduleService {

    private final CourseScheduleRepository courseScheduleRepository;
    private final CourseRepository courseRepository;
    private final CourseScheduleMapper mapper;

    @Override
    public CourseScheduleListDto getCourseScheduleListByCourse(UUID id) {
        log.info("invoke getCourseScheduleByCourse() method");
        var schedules = courseScheduleRepository.findAllByCourseId(id);
        var scheduleListDto = mapper.toCourseScheduleList(schedules);
        return new CourseScheduleListDto(scheduleListDto);
    }

    @Override
    public CourseScheduleDto getCourseScheduleById(UUID id) {
        log.info("invoke getCourseScheduleById() method");
        var schedule = courseScheduleRepository.findByIdAndDeletedHashIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule with id: " + id + " not found"));
        return mapper.toCourseScheduleDto(schedule);
    }

    @Override
    public CourseScheduleDto createCourseSchedule(CourseScheduleCreateRequest request) {
        log.info("invoke createCourseSchedule() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + " not found"));
        var schedule = mapper.toCourseSchedule(request, course);
        var savedSchedule = courseScheduleRepository.save(schedule);
        return mapper.toCourseScheduleDto(savedSchedule);
    }

    @Override
    public CourseScheduleDto updateCourseSchedule(CourseScheduleUpdateRequest request) {
        log.info("invoke updateCourseSchedule() method");
        var schedule = courseScheduleRepository.findByIdAndDeletedHashIsNull(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Schedule with id: " + request.id() + " not found"));
        if(schedule.getCourse().getId() != request.courseId()) {
            var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + " not found"));
            schedule.setCourse(course);
        }
        schedule.setCourseTitle(request.courseTitle());
        schedule.setLessonDate(request.lessonDate());
        schedule.setLessonStatus(request.lessonStatus());
        var savedSchedule = courseScheduleRepository.save(schedule);
        return mapper.toCourseScheduleDto(savedSchedule);
    }

    @Override
    public void deleteScheduleById(UUID id) {
        log.info("invoke deleteScheduleById() method");
        var schedule = courseScheduleRepository.findByIdAndDeletedHashIsNull(id)
                .orElse(null);
        if(schedule != null) {
            schedule.setDeletedAt(ZonedDateTime.now());
            schedule.setDeletedHash(UUID.randomUUID());
            courseScheduleRepository.save(schedule);
        }
    }
}
