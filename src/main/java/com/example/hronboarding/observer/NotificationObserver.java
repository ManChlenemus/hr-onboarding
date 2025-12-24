package com.example.hronboarding.observer;

import com.example.hronboarding.domain.Employee;
import com.example.hronboarding.domain.Task;
import com.example.hronboarding.notification.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationObserver implements HrObserver {

    private final NotificationClient notificationClient;

    @Override
    public void onEmployeeStatusChange(Employee employee) {
        notificationClient.notify(employee.getEmail(), "Статус онбординга", "Ваш статус: " + employee.getStatus());
    }

    @Override
    public void onTaskStatusChange(Task task) {
        var employee = task.getEmployee();
        if (employee != null) {
            notificationClient.notify(employee.getEmail(), "Статус задачи", task.getTitle() + " -> " + task.getStatus());
        }
    }
}
