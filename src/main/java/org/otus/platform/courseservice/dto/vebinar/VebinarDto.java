package org.otus.platform.courseservice.dto.vebinar;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record VebinarDto(
        UUID id,
        UUID teacherId,
        UUID courseId,
        String title,
        ZonedDateTime lessonDate,
        String summary,
        String task
) {
}
