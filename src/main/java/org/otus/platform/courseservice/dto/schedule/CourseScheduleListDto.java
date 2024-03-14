package org.otus.platform.courseservice.dto.schedule;

import java.util.List;

public record CourseScheduleListDto(
        List<CourseScheduleDto> schedules
) {
}
