package com.example.hronboarding.observer;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.Task;
import com.example.hronboarding.report.Report;
import com.example.hronboarding.report.ReportBuilder;
import com.example.hronboarding.repository.EmployeeRepository;
import com.example.hronboarding.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportObserver implements HrObserver {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private Report latestReport;

    @Override
    public void onEmployeeStatusChange(Employee employee) {
        regenerate();
    }

    @Override
    public void onTaskStatusChange(Task task) {
        regenerate();
    }

    private void regenerate() {
        var builder = new ReportBuilder()
                .addEmployees(employeeRepository.findAll())
                .addTasks(taskRepository.findAll());
        latestReport = builder.build();
    }

    public Report getLatestReport() {
        return latestReport;
    }
}
