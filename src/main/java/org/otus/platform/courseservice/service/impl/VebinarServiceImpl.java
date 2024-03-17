package org.otus.platform.courseservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.otus.platform.courseservice.dto.vebinar.VebinarCreateRequest;
import org.otus.platform.courseservice.dto.vebinar.VebinarDto;
import org.otus.platform.courseservice.dto.vebinar.VebinarListDto;
import org.otus.platform.courseservice.dto.vebinar.VebinarUpdateRequest;
import org.otus.platform.courseservice.mapper.VebinarMapper;
import org.otus.platform.courseservice.repository.CourseRepository;
import org.otus.platform.courseservice.repository.UserRepository;
import org.otus.platform.courseservice.repository.VebinarRepository;
import org.otus.platform.courseservice.service.VebinarService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class VebinarServiceImpl implements VebinarService {

    private final VebinarRepository vebinarRepository;
    private final CourseRepository courseRepository;
    private final VebinarMapper mapper;
    private final UserRepository userRepository;

    @Override
    public VebinarListDto getVebinarListByCourse(UUID id) {
        log.info("invoke getCourseScheduleByCourse() method");
        var vebinar = vebinarRepository.findAllByCourseId(id);
        var vebinarListDto = mapper.toVebinarList(vebinar);
        return new VebinarListDto(vebinarListDto);
    }

    @Override
    public VebinarDto getVebinarById(UUID id) {
        log.info("invoke getCourseScheduleById() method");
        var vebinar = vebinarRepository.findByIdAndDeletedHashIsNull(id)
                .orElseThrow(() -> new EntityNotFoundException("Schedule with id: " + id + " not found"));
        return mapper.toVebinarDto(vebinar);
    }

    @Override
    public VebinarDto createVebinar(VebinarCreateRequest request) {
        log.info("invoke createCourseSchedule() method");
        var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + " not found"));
        var teacher = userRepository.findByIdAndDeletedHashIsNull(request.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher with id: " + request.teacherId() + " not found"));
        var vebinar = mapper.toVebinar(request, course, teacher);
        var savedVebinar = vebinarRepository.save(vebinar);
        return mapper.toVebinarDto(savedVebinar);
    }

    @Override
    public VebinarDto updateVebinar(VebinarUpdateRequest request) {
        log.info("invoke updateCourseSchedule() method");
        var vebinar = vebinarRepository.findByIdAndDeletedHashIsNull(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Vebinar with id: " + request.id() + " not found"));
        if(vebinar.getCourse().getId() != request.courseId()) {
            var course = courseRepository.findByIdAndDeletedHashIsNull(request.courseId())
                    .orElseThrow(() -> new EntityNotFoundException("Course with id: " + request.courseId() + " not found"));
            vebinar.setCourse(course);
        }
        if(vebinar.getTeacher().getId() != request.teacherId()) {
            var teacher = userRepository.findByIdAndDeletedHashIsNull(request.teacherId())
                    .orElseThrow(() -> new EntityNotFoundException("Teacher with id: " + request.teacherId() + " not found"));
            vebinar.setTeacher(teacher);
        }
        vebinar.setTitle(request.title());
        vebinar.setLessonDate(request.lessonDate());
        vebinar.setSummary(request.summary());
        vebinar.setTask(request.task());
        var savedSchedule = vebinarRepository.save(vebinar);
        return mapper.toVebinarDto(savedSchedule);
    }

    @Override
    public void deleteVebinar(UUID id) {
        log.info("invoke deleteScheduleById() method");
        var vebinar = vebinarRepository.findByIdAndDeletedHashIsNull(id)
                .orElse(null);
        if(vebinar != null) {
            vebinar.setDeletedAt(ZonedDateTime.now());
            vebinar.setDeletedHash(UUID.randomUUID());
            vebinarRepository.save(vebinar);
        }
    }
}
