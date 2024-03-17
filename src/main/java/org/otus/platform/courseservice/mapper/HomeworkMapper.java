package org.otus.platform.courseservice.mapper;

import org.otus.platform.courseservice.dto.homework.HomeworkCreateRequest;
import org.otus.platform.courseservice.dto.homework.HomeworkDto;
import org.otus.platform.courseservice.model.homework.CompleteStatus;
import org.otus.platform.courseservice.model.homework.Homework;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.user.User;
import org.otus.platform.courseservice.model.vebinar.Vebinar;
import org.springframework.stereotype.Component;

@Component
public class HomeworkMapper {

    public HomeworkDto toHomeworkDto(Homework homework) {
        return HomeworkDto.builder()
                .id(homework.getId())
                .courseId(homework.getCourse().getId())
                .studentId(homework.getStudent().getId())
                .teacherId(homework.getTeacher().getId())
                .vebinarId(homework.getVebinar().getId())
                .added(homework.getAdded())
                .content(homework.getContent())
                .completeStatus(homework.getCompleteStatus())
                .build();
    }

    public Homework toHomework(HomeworkCreateRequest request, Course course, User student, User teacher, Vebinar vebinar) {
        return Homework.builder()
                .course(course)
                .student(student)
                .teacher(teacher)
                .vebinar(vebinar)
                .content(request.content())
                .completeStatus(CompleteStatus.InProgress)
                .build();
    }
}
