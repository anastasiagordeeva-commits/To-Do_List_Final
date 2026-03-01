package to_do_list.service;

import to_do_list.model.*;
import org.springframework.stereotype.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong();

    public List<Task> getAllTasks(){
        return new ArrayList<>(tasks.values());
    }

    public Task getById(Long requestId){
        if (tasks.containsKey(requestId)){
            return tasks.get(requestId);
        } throw new NoSuchElementException("Задачи с таким id не найдено.");
    }

    public Task createTask(Task requestTask){
        Task newTask = new Task();
        Long newId = id.incrementAndGet();
        newTask.setId(newId);
        newTask.setTitle(requestTask.getTitle());
        newTask.setDescription(requestTask.getDescription());
        newTask.setStatus(requestTask.getStatus());
        newTask.setCreationDate(requestTask.getCreationDate());
        newTask.setDeadline(requestTask.getDeadline());
        tasks.put(newId, newTask);
        return newTask;
    }

    public Task updateTask(Long requestId, Task requestTask){
        if (tasks.containsKey(requestId)){
            Task previousTask = tasks.get(requestId);
            previousTask.setTitle(requestTask.getTitle());
            previousTask.setDescription(requestTask.getDescription());
            previousTask.setStatus(requestTask.getStatus());
            previousTask.setCreationDate(requestTask.getCreationDate());
            previousTask.setDeadline(requestTask.getDeadline());
            return previousTask;
        } throw new NoSuchElementException("Задачи с таким id не найдено.");
    }

    public void deleteTask(Long requestId){
        if (tasks.containsKey(requestId)){
            tasks.remove(requestId);
        } else {
            throw new NoSuchElementException("Задачи с таким id не найдено.");
        }
    }
}
