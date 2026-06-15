package to_do_list.service;

import lombok.RequiredArgsConstructor;
import to_do_list.DTO.TaskRequest;
import to_do_list.DTO.TaskResponse;
import to_do_list.mapper.TaskMapper;
import to_do_list.model.*;
import org.springframework.stereotype.*;
import to_do_list.repository.TaskRepository;

import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;;
    private final TaskMapper mapper;

    public List<TaskResponse> getAllTasks(){
        return taskRepository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public TaskResponse getById(Long requestId){
        Task task = taskRepository.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("Задачи с таким id не найдено."));;
        return mapper.toResponse(task);
    }

    public TaskResponse createTask(TaskRequest requestTask){
        Task newTask = taskRepository.save(mapper.toEntity(requestTask));
        return mapper.toResponse(newTask);
    }

    public TaskResponse updateTask(Long requestId, TaskRequest requestTask){
        Task task = taskRepository.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("Задачи с таким id не найдено."));
        task.setTitle(requestTask.getTitle());
        task.setDescription(requestTask.getDescription());
        task.setStatus(requestTask.getStatus());
        task.setDeadline(requestTask.getDeadline());
        return mapper.toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long requestId){
        Task task = taskRepository.findById(requestId)
                .orElseThrow(() ->
                        new NoSuchElementException("Задачи с таким id не найдено."));
        taskRepository.delete(task);
    }

}
