package org.otus.platform.courseservice.dto.vebinar;

import java.util.List;

public record MonthVebinarListDto(
        Integer monthNumber,
        List<VebinarDto> vebinars
) {
}
