# TO-DO List REST Service
Сервис для управления задачами (To-Do list) на Java + Spring Boot.

## Функциональность
- Создание задачи (POST /tasks)
- Просмотр всех задач (GET /tasks)
- Просмотр задачи по ID (GET /tasks/{id})
- Обновление задачи (PUT /tasks/{id})
- Удаление задачи (DELETE /tasks/{id})
- Валидация входных данных
- Глобальная обработка ошибок
- Интеграционные тесты

## Используемые технологии
- Java 21
- Spring Boot 3.4.5
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven
- Lombok
- JUnit 5 + MockMvc
- Spring Web
- REST API

## Архитектура
Проект построен по трёхуровневой схеме:

(Controller) TaskController - принимает HTTP-запросы, валидирует входные DTO
(Service) TaskService - бизнес-логика, маппинг DTO-Entity
(Repository) TaskRepository - интерфейс JPA для работы с БД
(Model) Task, TaskStatus - JPA‑сущность и перечисление статусов
(DTO) TaskRequest, TaskResponse - объекты передачи данных
(Exception) GlobalExceptionHandler - глобальный обработчик ошибок

## Модель задачи (Task)

id (Long) - уникальный идентификатор, автоинкремент
title (String) - название задачи, не пустое, ≤ 100 символов
description (String) - описание, ≤ 500 символов (опционально)
status (TaskStatus) - статус выполнения: IN_PROGRESS, DONE, PAUSED
creationDate (LocalDate) - дата создания, автоматически (текущая дата)
deadline (LocalDate) - срок выполнения, не может быть раньше creationDate


### Предварительные требования
- JDK 21+
- Maven 3.8+
- (Опционально) PostgreSQL 15+

### Запуск
git clone https://github.com/anastasiagordeeva-commits/To-Do_List_Final
cd todo-list
./mvnw spring-boot:run
