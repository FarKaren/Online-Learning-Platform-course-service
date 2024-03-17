package org.otus.platform.courseservice.service;

import org.otus.platform.courseservice.dto.homework.HomeworkCreateRequest;
import org.otus.platform.courseservice.dto.homework.HomeworkDto;
import org.otus.platform.courseservice.dto.homework.HomeworkUpdateRequest;
import org.otus.platform.courseservice.dto.homework.UpdateHomeworkStatusRequest;

import java.util.UUID;

public interface HomeworkService {
    HomeworkDto getHomeworkById(UUID id);
    HomeworkDto createHomework(HomeworkCreateRequest request);
    HomeworkDto updateHomework(HomeworkUpdateRequest request);
    HomeworkDto updateHomeworkStatus(UpdateHomeworkStatusRequest request);
    void deleteHomework(UUID id);
}
