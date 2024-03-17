package org.otus.platform.courseservice.dto.vebinar;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record VebinarUpdateRequest(
        @NotNull
        UUID id,
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
