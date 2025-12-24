package com.example.hronboarding.report;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReportBuilder {
    private final StringBuilder builder = new StringBuilder();

    public ReportBuilder addEmployees(List<Employee> employees) {
        builder.append("Сотрудники (").append(employees.size()).append(")").append(System.lineSeparator());
        employees.forEach(emp -> builder.append("- ")
                .append(emp.getFullName())
                .append(" [").append(emp.getStatus()).append("]")
                .append(" позиция: ").append(emp.getPosition())
                .append(System.lineSeparator()));
        builder.append(System.lineSeparator());
        return this;
    }

    public ReportBuilder addTasks(List<Task> tasks) {
        builder.append("Задачи (").append(tasks.size()).append(")").append(System.lineSeparator());
        tasks.forEach(task -> builder.append("- ")
                .append(task.getTitle())
                .append(" [").append(task.getStatus()).append("]")
                .append(" due: ").append(task.getDueDate())
                .append(task.getEmployee() != null ? " for " + task.getEmployee().getFullName() : "")
                .append(System.lineSeparator()));
        builder.append(System.lineSeparator());
        return this;
    }

    public Report build() {
        String title = "Отчет HR за " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return new Report(title, builder.toString());
    }
}
