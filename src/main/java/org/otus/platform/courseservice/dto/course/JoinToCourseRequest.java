package org.otus.platform.courseservice.dto.course;

import jakarta.validation.constraints.NotNull;
import org.otus.platform.courseservice.model.user.UserType;

import java.util.UUID;

public record JoinToCourseRequest(
        @NotNull
        UUID userId,
        @NotNull
        UUID courseId,
        @NotNull
        UserType userType
) {
}
