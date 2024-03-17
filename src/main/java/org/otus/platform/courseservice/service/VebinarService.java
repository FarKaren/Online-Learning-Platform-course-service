package org.otus.platform.courseservice.service;

import org.otus.platform.courseservice.dto.vebinar.VebinarCreateRequest;
import org.otus.platform.courseservice.dto.vebinar.VebinarDto;
import org.otus.platform.courseservice.dto.vebinar.VebinarListDto;
import org.otus.platform.courseservice.dto.vebinar.VebinarUpdateRequest;

import java.util.UUID;

public interface VebinarService {
    VebinarListDto getVebinarListByCourse(UUID id);
    VebinarDto getVebinarById(UUID id);
    VebinarDto createVebinar(VebinarCreateRequest request);
    VebinarDto updateVebinar(VebinarUpdateRequest request);
    void deleteVebinar(UUID id);
}
