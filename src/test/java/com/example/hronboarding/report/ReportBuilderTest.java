package com.example.hronboarding.report;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.EmployeeStatus;
import com.example.hronboarding.domain.Task;
import com.example.hronboarding.domain.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportBuilderTest {

    @Test
    void buildsReportWithEmployeesAndTasks() {
        var employee = new Employee();
        employee.setFullName("Jane Doe");
        employee.setEmail("jane@example.com");
        employee.setPosition("HR Manager");
        employee.setStartDate(LocalDate.now());
        employee.setStatus(EmployeeStatus.ACTIVE);

        var task = new Task();
        task.setTitle("Настроить доступ");
        task.setDescription("VPN + почта");
        task.setDueDate(LocalDate.now().plusDays(1));
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setEmployee(employee);

        var report = new ReportBuilder()
                .addEmployees(List.of(employee))
                .addTasks(List.of(task))
                .build();

        var text = report.toString();
        assertTrue(text.contains("Jane Doe"));
        assertTrue(text.contains("HR Manager"));
        assertTrue(text.contains("Настроить доступ"));
        assertTrue(text.contains("IN_PROGRESS"));
    }
}
