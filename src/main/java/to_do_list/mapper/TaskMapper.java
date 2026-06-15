package to_do_list.mapper;

import org.springframework.stereotype.Component;
import to_do_list.DTO.TaskRequest;
import to_do_list.DTO.TaskResponse;
import to_do_list.model.Task;

import java.time.LocalDate;

@Component
public class TaskMapper {

    public Task toEntity(TaskRequest request){
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setCreationDate(LocalDate.now());
        task.setDeadline(request.getDeadline());
        return task;
    }

    public TaskResponse toResponse(Task task){
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .creationDate(task.getCreationDate())
                .deadline(task.getDeadline())
                .build();
    }
}
