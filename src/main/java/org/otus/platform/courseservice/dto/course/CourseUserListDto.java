package org.otus.platform.courseservice.dto.course;

import org.otus.platform.courseservice.dto.UserDto;

import java.util.List;

public record CourseUserListDto(
        List<UserDto> users
) {
}
