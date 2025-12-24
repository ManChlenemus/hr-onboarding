package com.example.hronboarding.service;

import com.example.hronboarding.domain.EmployeeStatus;
import com.example.hronboarding.domain.TaskStatus;
import com.example.hronboarding.dto.EmployeeRequest;
import com.example.hronboarding.dto.TaskRequest;
import com.example.hronboarding.observer.ReportObserver;
import com.example.hronboarding.repository.EmployeeRepository;
import com.example.hronboarding.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HrDeskFacadeIntegrationTest {

    @Autowired
    private HrDeskFacade facade;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ReportObserver reportObserver;

    @Test
    void hireAndTaskLifecycle() {
        var empReq = new EmployeeRequest(
                "Ivan Ivanov",
                "ivan@example.com",
                "Developer",
                LocalDate.now());

        var employee = facade.hire(empReq);
        assertEquals(EmployeeStatus.ONBOARDING, employee.getStatus());

        var taskReq = new TaskRequest(
                "Выдать ноутбук",
                "MacBook Air",
                LocalDate.now().plusDays(3));

        var task = facade.addTask(employee.getId(), taskReq);
        assertEquals(TaskStatus.TODO, task.getStatus());

        facade.updateStatus(employee.getId(), EmployeeStatus.ACTIVE);
        var updatedEmployee = employeeRepository.findById(employee.getId()).orElseThrow();
        assertEquals(EmployeeStatus.ACTIVE, updatedEmployee.getStatus());

        facade.updateTaskStatus(task.getId(), TaskStatus.DONE);
        var updatedTask = taskRepository.findById(task.getId()).orElseThrow();
        assertEquals(TaskStatus.DONE, updatedTask.getStatus());

        var report = reportObserver.getLatestReport();
        assertNotNull(report);
        var text = report.toString();
        assertTrue(text.contains("Ivan Ivanov"));
        assertTrue(text.contains("Выдать ноутбук"));
    }
}
