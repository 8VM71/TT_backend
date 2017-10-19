package tpu.timetracker.backend.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tpu.timetracker.backend.model.Project;
import tpu.timetracker.backend.model.Task;

import java.util.Collection;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TaskRepository extends JpaRepository<Task, String> {
  Optional<Task> findByProject(Project p);
  Collection<Task> findAllByProject(Project p);
}