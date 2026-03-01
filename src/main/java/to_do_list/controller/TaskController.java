package to_do_list.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import to_do_list.model.*;
import org.springframework.web.bind.annotation.*;
import to_do_list.service.TaskService;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        try { return ResponseEntity.ok(taskService.getById(id));
        } catch (NoSuchElementException e) { return ResponseEntity.notFound().build(); }

    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task requestTask){
        try { return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(requestTask));
        } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().build(); }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task requestTask){
        try {
            return ResponseEntity.ok(taskService.updateTask(id, requestTask));
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        try{
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }
}
