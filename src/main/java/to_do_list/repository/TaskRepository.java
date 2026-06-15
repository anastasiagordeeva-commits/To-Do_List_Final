package to_do_list.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import to_do_list.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
