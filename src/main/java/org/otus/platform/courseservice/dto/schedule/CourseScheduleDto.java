package org.otus.platform.courseservice.dto.schedule;

import lombok.Builder;
import org.otus.platform.courseservice.model.schedule.LessonStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record CourseScheduleDto(
        UUID scheduleId,
        UUID courseId,
        String courseTitle,
        ZonedDateTime lessonDate,
        LessonStatus lessonStatus,
        ZonedDateTime rescheduleToDate
) {
}
