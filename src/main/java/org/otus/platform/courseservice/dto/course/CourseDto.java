package org.otus.platform.courseservice.dto.course;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record CourseDto(
        UUID id,
        String title,
        BigDecimal price,
        ZonedDateTime added,
        ZonedDateTime startAt,
        ZonedDateTime endAt
) {
}
