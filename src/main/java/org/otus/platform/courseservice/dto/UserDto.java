package org.otus.platform.courseservice.dto;


import lombok.Builder;
import org.otus.platform.courseservice.model.user.UserRole;

import java.util.UUID;

@Builder
public record UserDto(
        UUID id,
        String name,
        String email,
        String phone,
        String password,
        UserRole role
) {
}

