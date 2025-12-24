# HR Onboarding Demo

Управление онбордингом/оффбордингом сотрудников. Технологии: Java 17, Spring Boot, Gradle, JPA (H2 in-memory), OpenAPI/Swagger, AOP (аудит), Observer (уведомления и отчеты), Builder (отчет).

## Что есть внутри
- REST API для сотрудников и задач.
- Фасад `HrDeskFacade` для бизнес-логики.
- Наблюдатели: уведомления (стаб, логирует) и автообновление отчета.
- Аспект `@Audit` логирует вызовы.
- Отчет `ReportBuilder` (вендорский билд, можно посмотреть через endpoint).
- Тесты: интеграционный на фасад и юнит на билдер отчета.

## Требования
- JDK 17+
- Gradle 8.7+ или gradle wrapper (генерируется командой ниже)

## Быстрый старт
```bash
cd hr-onboarding
gradle wrapper --gradle-version 8.7   # один раз, если нет gradlew
./gradlew test jacocoTestReport       # сборка + тесты + отчет покрытия
./gradlew bootRun                     # запуск приложения (останавливать Ctrl+C)
```
Windows PowerShell:
```powershell
cd hr-onboarding
gradle wrapper --gradle-version 8.7   # если gradlew отсутствует
.\gradlew.bat test jacocoTestReport
.\gradlew.bat bootRun
```

## Проверка вручную
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- H2 console: http://localhost:8080/h2-console  
  JDBC URL: `jdbc:h2:mem:hr;MODE=PostgreSQL`, user `sa`, пароль пустой.

### Минимальный сценарий (curl)
```bash
# создать сотрудника
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Ivan Ivanov","email":"ivan@example.com","position":"Developer","startDate":"2024-12-24"}'

# сменить статус
curl -X PUT "http://localhost:8080/api/employees/1/status?status=ACTIVE"

# добавить задачу
curl -X POST http://localhost:8080/api/tasks/employee/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Выдать ноутбук","description":"MacBook Air","dueDate":"2024-12-30"}'

# завершить задачу
curl -X PUT "http://localhost:8080/api/tasks/1/status?status=DONE"

# получить отчет
curl http://localhost:8080/api/reports/latest
```
В логах будут строки `AUDIT ...` и `Notify -> ...` (стаб уведомлений).

## Тесты и покрытие
- Запуск: `./gradlew test jacocoTestReport`
- Отчеты:
  - Тесты: `build/reports/tests/test/index.html`
  - Jacoco: `build/reports/jacoco/test/html/index.html`

## Кратко по коду
- Домены: `Employee`, `Task`, enum статусы.
- REST: `EmployeeController`, `TaskController`, `ReportController`.
- Логика: `HrDeskFacade`.
- Наблюдатели: `NotificationObserver`, `ReportObserver`.
- Аспект: `AuditAspect` + аннотация `@Audit`.
- Отчет: `ReportBuilder`, `Report`.
