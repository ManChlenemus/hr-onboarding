package com.example.hronboarding.observer;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.Task;

public interface HrObserver {
    void onEmployeeStatusChange(Employee employee);
    void onTaskStatusChange(Task task);
}
