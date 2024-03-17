package org.otus.platform.courseservice.mapper;

import org.otus.platform.courseservice.dto.vebinar.VebinarCreateRequest;
import org.otus.platform.courseservice.dto.vebinar.VebinarDto;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.user.User;
import org.otus.platform.courseservice.model.vebinar.Vebinar;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VebinarMapper {

    public List<VebinarDto> toVebinarList(List<Vebinar> vebinar) {
        return vebinar.stream().map(this::toVebinarDto).toList();
    }

    public VebinarDto toVebinarDto(Vebinar vebinar) {
        return VebinarDto.builder()
                .id(vebinar.getId())
                .courseId(vebinar.getCourse().getId())
                .lessonDate(vebinar.getLessonDate())
                .title(vebinar.getTitle())
                .summary(vebinar.getSummary())
                .teacherId(vebinar.getTeacher().getId())
                .task(vebinar.getTask())
                .build();
    }

    public Vebinar toVebinar(VebinarCreateRequest dto, Course course, User teacher) {
        return Vebinar.builder()
                .course(course)
                .title(dto.title())
                .lessonDate(dto.lessonDate())
                .summary(dto.summary())
                .teacher(teacher)
                .task(dto.task())
                .build();
    }
}
