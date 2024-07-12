package com.domain.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.Project;
import com.domain.models.Tasks;
import com.domain.repository.ProjectRepository;

@Service
public class ProjectTaskService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectTaskService.class);

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

    public Project addTaskToProject(String projectId, Tasks task) {
        Tasks createdTask = taskService.createTask(task);
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.getTasks().add(createdTask);
            return projectRepository.save(project);
        } else {
            logger.error("Project not found: {}", projectId);
            throw new RuntimeException("Project not found");
        }
    }

    public Project updateTaskInProject(String projectId, Tasks task) {
        logger.info("Updating task {} in project {}", task.getTasksId(), projectId);
        taskService.updateTask(task.getTasksId(), task);
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            boolean taskFound = false;
            for (int i = 0; i < project.getTasks().size(); i++) {
                if (project.getTasks().get(i).getTasksId().equals(task.getTasksId())) {
                    project.getTasks().set(i, task);
                    taskFound = true;
                    break;
                }
            }
            if (!taskFound) {
                logger.error("Task not found in project: {}", task.getTasksId());
                throw new RuntimeException("Task not found in project");
            }
            return projectRepository.save(project);
        } else {
            logger.error("Project not found: {}", projectId);
            throw new RuntimeException("Project not found");
        }
    }

    public Project deleteTaskFromProject(String projectId, String taskId) {
      logger.info("Deleting task {} from project {}", taskId, projectId);
      Optional<Project> optionalProject = projectRepository.findById(projectId);

      if (optionalProject.isPresent()) {
          Project project = optionalProject.get();
          boolean taskRemoved = project.getTasks().removeIf(t -> t.getTasksId().equals(taskId));
          if (taskRemoved) {
              taskService.deleteTask(taskId);
              return projectRepository.save(project);
          } else {
              logger.error("Task not found in project: {}", taskId);
              throw new RuntimeException("Task not found in project");
          }
      } else {
          logger.error("Project not found: {}", projectId);
          throw new RuntimeException("Project not found");
      }
  }
}
