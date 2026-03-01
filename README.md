# To-Do List REST API (Spring Boot)
REST API для управления списком задач (To-Do List), реализованный на Spring Boot.
Реализован полноценный CRUD функционал без использования базы данных.

## Функциональность
- Создание задачи
- Получение всех задач
- Получение задачи по ID
- Обновление задачи
- Удаление задачи

## Используемые технологии
- Java
- Spring Boot
- Spring Web
- REST API
- ConcurrentHashMap
- AtomicLong

## Архитектура
Проект разделён на слои:
- Controller: обработка HTTP-запросов
- Service: бизнес-логика
- Model: модель данных (Task, TaskStatus)


## Модель задачи
Task содержит:
- id (Long)
- title (String)
- description (String)
- status (enum: IN_PROGRESS, DONE, PAUSED)
- creationDate (LocalDate)
- deadline (LocalDate)

## API Endpoints
Получить все задачи - GET /tasks
Получить задачу по ID - GET /tasks/{id}
Создать задачу - POST /tasks
Обновить задачу - PUT /tasks/{id}
Удалить задачу - DELETE /tasks/{id}
