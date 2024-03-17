package org.otus.platform.courseservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.otus.platform.courseservice.dto.homework.HomeworkCreateRequest;
import org.otus.platform.courseservice.dto.homework.HomeworkDto;
import org.otus.platform.courseservice.dto.homework.HomeworkUpdateRequest;
import org.otus.platform.courseservice.dto.homework.UpdateHomeworkStatusRequest;
import org.otus.platform.courseservice.service.HomeworkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course/homework")
@Validated
@RequiredArgsConstructor
public class HomeworkController {

    private final HomeworkService homeworkService;

    @GetMapping("/{id}")
    ResponseEntity<HomeworkDto> getHomeworkById(@NotNull @PathVariable UUID id) {
        var response = homeworkService.getHomeworkById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    ResponseEntity<HomeworkDto> createHomework(@Valid @RequestBody HomeworkCreateRequest request) {
        var response = homeworkService.createHomework(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    ResponseEntity<HomeworkDto> updateHomework(@Valid @RequestBody HomeworkUpdateRequest request) {
        var response = homeworkService.updateHomework(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteHomework(@NotNull @PathVariable UUID id) {
        homeworkService.deleteHomework(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/update/homework/status")
    ResponseEntity<HomeworkDto> updateHomeworkStatus(@Valid @RequestBody UpdateHomeworkStatusRequest request) {
        var response = homeworkService.updateHomeworkStatus(request);
        return ResponseEntity.ok(response);
    }
}
