package org.otus.platform.courseservice.dto.course;

import java.util.List;

public record CourseListDto(
        List<CourseDto> courses
) {
}
