package org.otus.platform.courseservice.dto.course;

import com.fasterxml.jackson.annotation.JsonFormat;
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
        ZonedDateTime startAt
) {
}
