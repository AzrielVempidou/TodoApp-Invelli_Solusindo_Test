package com.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.domain.models.Project;
import com.domain.models.Tasks;
import com.domain.services.ProjectTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/projects")
public class ProjectTaskController {

    private static final Logger logger = LoggerFactory.getLogger(ProjectTaskController.class);

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<Project> addTaskToProject(@PathVariable String projectId, @RequestBody Tasks task) {
        try {
            Project updatedProject = projectTaskService.addTaskToProject(projectId, task);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            logger.error("Error adding task to project: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/{projectId}/tasks")
    public ResponseEntity<Project> updateTaskInProject(@PathVariable String projectId, @RequestBody Tasks task) {
        try {
            logger.info("Received request to update task in project {}", projectId);
            if (task.getTasksId() == null) {
                logger.error("Task ID is null");
                return ResponseEntity.badRequest().body(null);
            }
            Project updatedProject = projectTaskService.updateTaskInProject(projectId, task);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            logger.error("Error updating task in project: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<Project> deleteTaskFromProject(@PathVariable String projectId, @PathVariable String taskId) {
        try {
            Project updatedProject = projectTaskService.deleteTaskFromProject(projectId, taskId);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            logger.error("Error deleting task from project: {}", e.getMessage());
            return ResponseEntity.status(404).body(null);
        }
    }

}
