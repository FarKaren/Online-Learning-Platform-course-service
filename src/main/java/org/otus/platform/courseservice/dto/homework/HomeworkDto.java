package org.otus.platform.courseservice.dto.homework;

import lombok.Builder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record HomeworkDto(
        UUID id,
        UUID course,
        UUID student,
        UUID teacher,
        Boolean onReview,
        ZonedDateTime added
) {
}
