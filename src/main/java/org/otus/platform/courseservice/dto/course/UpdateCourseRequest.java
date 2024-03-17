package org.otus.platform.courseservice.dto.course;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public record UpdateCourseRequest(
        @NotNull
        UUID id,
        @NotNull
        String title,
        @NotNull
        BigDecimal price,
        @NotNull
        ZonedDateTime startAt,
        @NotNull
        ZonedDateTime endAt
) {
}
