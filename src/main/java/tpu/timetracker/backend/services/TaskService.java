package tpu.timetracker.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tpu.timetracker.backend.jpa.TaskRepository;
import tpu.timetracker.backend.model.Project;
import tpu.timetracker.backend.model.Task;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private ProjectService projectService;

  public Optional<Task> createTask(Project p) {
    Objects.requireNonNull(p);

    if ( ! projectService.projectExist(p.getId())){
      throw new SecurityException(String.format("Task with that id: %s does not exist!", p.getId()));
    }

    Task task = new Task(p);
    return Optional.of(taskRepository.save(task));
  }

  public void deleteTask(Task t) {
    Objects.requireNonNull(t);
    taskRepository.delete(t);
  }

  public void deleteTask(String id) {
    Objects.requireNonNull(id);
    taskRepository.delete(id);
  }

  public Collection<Task> getAllProjectTasks(Project p) {
    Objects.requireNonNull(p);

    return taskRepository.findAllByProject(p);
  }

  public Optional<Task> getTaskById(String id) {
    Objects.requireNonNull(id);
    return Optional.ofNullable(taskRepository.findOne(id));
  }

  public boolean taskExist(String id) {
    return getTaskById(id).isPresent();
  }
}
