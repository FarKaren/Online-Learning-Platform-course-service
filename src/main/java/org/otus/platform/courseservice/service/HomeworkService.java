package org.otus.platform.courseservice.service;

import org.otus.platform.courseservice.dto.homework.HomeworkCreateRequest;
import org.otus.platform.courseservice.dto.homework.HomeworkDto;
import org.otus.platform.courseservice.dto.homework.HomeworkUpdateRequest;

import java.util.UUID;

public interface HomeworkService {
    HomeworkDto getHomeworkById(UUID id);
    HomeworkDto createHomework(HomeworkCreateRequest request);
    HomeworkDto updateHomework(HomeworkUpdateRequest request);
    void deleteHomework(UUID id);
}
