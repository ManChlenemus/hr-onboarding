package com.example.hronboarding.web;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.EmployeeStatus;
import com.example.hronboarding.dto.EmployeeRequest;
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
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final HrDeskFacade facade;

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(facade.hire(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Employee> changeStatus(@PathVariable Long id, @RequestParam EmployeeStatus status) {
        return ResponseEntity.ok(facade.updateStatus(id, status));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> all() {
        return ResponseEntity.ok(facade.listEmployees());
    }
}
