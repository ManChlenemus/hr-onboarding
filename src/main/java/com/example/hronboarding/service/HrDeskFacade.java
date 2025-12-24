package com.example.hronboarding.service;

import com.example.hronboarding.audit.Audit;
import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.EmployeeStatus;
import com.example.hronboarding.domain.Task;
import com.example.hronboarding.domain.TaskStatus;
import com.example.hronboarding.dto.EmployeeRequest;
import com.example.hronboarding.dto.TaskRequest;
import com.example.hronboarding.observer.HrObserver;
import com.example.hronboarding.repository.EmployeeRepository;
import com.example.hronboarding.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HrDeskFacade {

    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final List<HrObserver> observers;

    @Audit("create-employee")
    @Transactional
    public Employee hire(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFullName(request.fullName());
        employee.setEmail(request.email());
        employee.setPosition(request.position());
        employee.setStartDate(request.startDate());
        employee.setStatus(EmployeeStatus.ONBOARDING);
        Employee saved = employeeRepository.save(employee);
        observers.forEach(o -> o.onEmployeeStatusChange(saved));
        return saved;
    }

    @Audit("update-employee-status")
    @Transactional
    public Employee updateStatus(Long id, EmployeeStatus status) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setStatus(status);
        Employee saved = employeeRepository.save(employee);
        observers.forEach(o -> o.onEmployeeStatusChange(saved));
        return saved;
    }

    @Audit("create-task")
    @Transactional
    public Task addTask(Long employeeId, TaskRequest request) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        Task task = new Task();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setStatus(TaskStatus.TODO);
        task.setEmployee(employee);
        Task saved = taskRepository.save(task);
        observers.forEach(o -> o.onTaskStatusChange(saved));
        return saved;
    }

    @Audit("task-status")
    @Transactional
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(status);
        Task saved = taskRepository.save(task);
        observers.forEach(o -> o.onTaskStatusChange(saved));
        return saved;
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }
}
