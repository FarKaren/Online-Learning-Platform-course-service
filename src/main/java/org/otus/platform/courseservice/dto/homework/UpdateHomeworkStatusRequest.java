package org.otus.platform.courseservice.dto.homework;

import jakarta.validation.constraints.NotNull;
import org.otus.platform.courseservice.model.homework.CompleteStatus;

import java.util.UUID;

public record UpdateHomeworkStatusRequest(
        @NotNull
        UUID homeworkId,
        @NotNull
        CompleteStatus completeStatus
) {
}
