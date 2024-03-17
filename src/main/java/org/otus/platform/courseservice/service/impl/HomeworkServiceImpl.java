package org.otus.platform.courseservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.courseservice.dto.homework.HomeworkCreateRequest;
import org.otus.platform.courseservice.dto.homework.HomeworkDto;
import org.otus.platform.courseservice.dto.homework.HomeworkUpdateRequest;
import org.otus.platform.courseservice.dto.homework.UpdateHomeworkStatusRequest;
import org.otus.platform.courseservice.mapper.HomeworkMapper;
import org.otus.platform.courseservice.model.homework.CompleteStatus;
import org.otus.platform.courseservice.repository.CourseRepository;
import org.otus.platform.courseservice.repository.HomeworkRepository;
import org.otus.platform.courseservice.repository.UserRepository;
import org.otus.platform.courseservice.service.HomeworkService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final HomeworkMapper mapper;

    @Override
    public HomeworkDto getHomeworkById(UUID id) {
        log.info("invoke getHomeworkById() method");
        var homework = homeworkRepository.findByIdAndDeletedHashIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Homework with id: " + id + " not found"));
        return mapper.toHomeworkDto(homework);
    }

    @Override
    public HomeworkDto createHomework(HomeworkCreateRequest request) {
        log.info("invoke createHomework() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + " not found"));
        var student = userRepository.findByIdAndDeletedHashIsNull(request.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student with id: " + request.studentId() + " not found"));
        var teacher = userRepository.findByIdAndDeletedHashIsNull(request.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Student with id: " + request.teacherId() + " not found"));
        var homework = mapper.toHomework(request, course, student, teacher);
        var savedHomework = homeworkRepository.save(homework);
        return mapper.toHomeworkDto(savedHomework);
    }

    @Override
    public HomeworkDto updateHomework(HomeworkUpdateRequest request) {
        log.info("invoke updateHomework() method");
        var homework = homeworkRepository.findByIdAndDeletedHashIsNull(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Homework with id: " + request.id() + " not found"));
        if(homework.getCourse().getId() != request.courseId()) {
            var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + " not found"));
            homework.setCourse(course);
        }
        if(homework.getStudent().getId() != request.studentId()) {
            var student = userRepository.findByIdAndDeletedHashIsNull(request.studentId())
                    .orElseThrow(() -> new EntityNotFoundException("Student with id: " + request.studentId() + " not found"));
            homework.setStudent(student);
        }
        if(homework.getTeacher().getId() != request.teacherId()) {
            var teacher = userRepository.findByIdAndDeletedHashIsNull(request.teacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Student with id: " + request.teacherId() + " not found"));
            homework.setTeacher(teacher);
        }
        homework.setCompleteStatus(CompleteStatus.InProgress);
        var savedHomework = homeworkRepository.save(homework);
        return mapper.toHomeworkDto(savedHomework);
    }

    @Override
    public HomeworkDto updateHomeworkStatus(UpdateHomeworkStatusRequest request) {
        log.info("invoke updateHomeworkStatus() method");
        var homework = homeworkRepository.findByIdAndDeletedHashIsNull(request.homeworkId())
                .orElseThrow(() -> new EntityNotFoundException("Homework with id: " + request.homeworkId() + " not found"));
        homework.setCompleteStatus(request.completeStatus());
        var savedHomework = homeworkRepository.save(homework);
        return mapper.toHomeworkDto(savedHomework);
    }

    @Override
    public void deleteHomework(UUID id) {
        log.info("invoke deleteHomework() method");
        var homework = homeworkRepository.findByIdAndDeletedHashIsNull(id)
                .orElse(null);
        if(homework != null) {
            homework.setDeletedAt(ZonedDateTime.now());
            homework.setDeletedHash(UUID.randomUUID());
            homeworkRepository.save(homework);
        }
    }
}
