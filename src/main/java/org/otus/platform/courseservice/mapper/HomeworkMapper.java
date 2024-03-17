package org.otus.platform.courseservice.mapper;

import org.otus.platform.courseservice.dto.homework.HomeworkCreateRequest;
import org.otus.platform.courseservice.dto.homework.HomeworkDto;
import org.otus.platform.courseservice.model.homework.CompleteStatus;
import org.otus.platform.courseservice.model.homework.Homework;
import org.otus.platform.courseservice.model.course.Course;
import org.otus.platform.courseservice.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class HomeworkMapper {

    public HomeworkDto toHomeworkDto(Homework homework) {
        return HomeworkDto.builder()
                .id(homework.getId())
                .course(homework.getCourse().getId())
                .student(homework.getStudent().getId())
                .teacher(homework.getTeacher().getId())
                .content(homework.getContent())
                .completeStatus(homework.getCompleteStatus())
                .build();
    }

    public Homework toHomework(HomeworkCreateRequest request, Course course, User student, User teacher) {
        return Homework.builder()
                .course(course)
                .student(student)
                .teacher(teacher)
                .content(request.content())
                .completeStatus(CompleteStatus.InProgress)
                .build();
    }
}
