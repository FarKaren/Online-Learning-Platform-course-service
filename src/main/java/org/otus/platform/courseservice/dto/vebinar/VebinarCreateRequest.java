package org.otus.platform.courseservice.dto.vebinar;

import jakarta.validation.constraints.NotNull;

import java.time.ZonedDateTime;
import java.util.UUID;

public record VebinarCreateRequest(
        @NotNull
        UUID courseId,
        @NotNull
        String title,
        @NotNull
        ZonedDateTime lessonDate,
        @NotNull
        String summary,
        @NotNull
        UUID teacherId,
        @NotNull
        String task
) {
}
