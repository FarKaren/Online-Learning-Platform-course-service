package org.otus.platform.courseservice.dto.homework;

import lombok.Builder;
import org.otus.platform.courseservice.model.homework.CompleteStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
public record HomeworkDto(
        UUID id,
        UUID course,
        UUID student,
        UUID teacher,
        CompleteStatus completeStatus,
        ZonedDateTime added,
        String content
) {
}
