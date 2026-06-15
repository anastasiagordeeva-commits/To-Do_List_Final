package to_do_list.DTO;


import lombok.*;
import to_do_list.model.TaskStatus;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate creationDate;
    private LocalDate deadline;
    private Long id;
}
