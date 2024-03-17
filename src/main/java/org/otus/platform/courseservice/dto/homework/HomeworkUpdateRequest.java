package org.otus.platform.courseservice.dto.homework;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record HomeworkUpdateRequest(
        @NotNull
        UUID id,
        @NotNull
        UUID courseId,
        @NotNull
        UUID studentId,
        @NotNull
        UUID teacherId,
        @NotNull
        UUID vebinarId,
        @NotNull
        String content
) {
}
