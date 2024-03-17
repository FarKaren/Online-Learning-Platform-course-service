package org.otus.platform.courseservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.courseservice.dto.vebinar.VebinarCreateRequest;
import org.otus.platform.courseservice.dto.vebinar.VebinarDto;
import org.otus.platform.courseservice.dto.vebinar.VebinarListDto;
import org.otus.platform.courseservice.dto.vebinar.VebinarUpdateRequest;
import org.otus.platform.courseservice.service.VebinarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course/vebinar")
@Validated
@RequiredArgsConstructor
public class VebinarController {

    private final VebinarService vebinarService;

    @GetMapping("/list/{id}")
    ResponseEntity<VebinarListDto> getVebinarListByCourse(@NotNull @PathVariable UUID id) {
        var response = vebinarService.getVebinarListByCourse(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    ResponseEntity<VebinarDto> getCVebinarById(@NotNull @PathVariable UUID id) {
        var response = vebinarService.getVebinarById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    ResponseEntity<VebinarDto> createVebinar(@Valid @RequestBody VebinarCreateRequest request) {
        var response = vebinarService.createVebinar(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    ResponseEntity<VebinarDto> updateVebinar(@Valid @RequestBody VebinarUpdateRequest request) {
        var response = vebinarService.updateVebinar(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteVebinarById(@NotNull @PathVariable UUID id) {
        vebinarService.deleteVebinar(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
