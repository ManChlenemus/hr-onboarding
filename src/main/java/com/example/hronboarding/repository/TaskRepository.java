package com.example.hronboarding.repository;

import com.example.hronboarding.domain.Task;
import com.example.hronboarding.domain.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByEmployeeId(Long employeeId);
    List<Task> findByStatus(TaskStatus status);
}
