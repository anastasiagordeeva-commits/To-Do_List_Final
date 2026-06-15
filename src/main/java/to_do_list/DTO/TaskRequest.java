package to_do_list.DTO;


import jakarta.validation.constraints.*;
import lombok.*;
import to_do_list.model.TaskStatus;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    @NotBlank(message = "Title can not be empty")
    @Size(max = 100,  message = "Title cannot exceed 100 characters.")
    private String title;
    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;
    @NotNull(message = "Status can not be empty.")
    private TaskStatus status;
    @NotNull(message = "Deadline can not be empty.")
    private LocalDate deadline;
}
