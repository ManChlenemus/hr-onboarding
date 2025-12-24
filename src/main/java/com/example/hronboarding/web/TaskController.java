package com.example.hronboarding.web;

import com.example.hronboarding.domain.Task;
import com.example.hronboarding.domain.TaskStatus;
import com.example.hronboarding.dto.TaskRequest;
import com.example.hronboarding.service.HrDeskFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final HrDeskFacade facade;

    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<Task> create(@PathVariable Long employeeId, @Valid @RequestBody TaskRequest request) {
        return ResponseEntity.ok(facade.addTask(employeeId, request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> changeStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return ResponseEntity.ok(facade.updateTaskStatus(id, status));
    }

    @GetMapping
    public ResponseEntity<List<Task>> all() {
        return ResponseEntity.ok(facade.listTasks());
    }
}
