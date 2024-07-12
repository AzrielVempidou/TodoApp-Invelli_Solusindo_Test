package com.domain.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.domain.models.Tasks;
import com.domain.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  // Create
  @PostMapping("/create")
  public ResponseEntity<Tasks> createTask(@RequestBody Tasks task) {
    return ResponseEntity.ok(taskService.createTask(task));
  }

  // Read all
  @GetMapping("/all")
  public ResponseEntity<List<Tasks>> getAllTasks() {
    return ResponseEntity.ok(taskService.getAllTasks());
  }

  // Sort by title
  @GetMapping("/sort/title")
  public ResponseEntity<List<Tasks>> sortByTitle() {
    return ResponseEntity.ok(taskService.sortByTitle());
  }

  // Sort by priority
  @GetMapping("/sort/priority")
  public ResponseEntity<List<Tasks>> sortByPriority() {
    return ResponseEntity.ok(taskService.sortByPriority());
  }

  // Sort by deadline
  @GetMapping("/sort/deadline")
  public ResponseEntity<List<Tasks>> sortByDeadline() {
    return ResponseEntity.ok(taskService.sortByDeadline());
  }

  // Filter by deadline
  @GetMapping("/filter/deadline")
  public ResponseEntity<List<Tasks>> filterByDeadline(
      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline) {
    return ResponseEntity.ok(taskService.filterByDeadline(deadline));
  }

  // Update task completion status
  @PutMapping("/update/completion/{id}")
  public ResponseEntity<Tasks> updateTaskCompletion(@PathVariable String id, @RequestParam boolean completed) {
    return ResponseEntity.ok(taskService.updateTaskCompletion(id, completed));
  }

  // Update task details
  @PutMapping("/{id}")
  public ResponseEntity<Tasks> updateTask(@PathVariable("id") String id, @RequestBody Tasks updatedTask) {
    Tasks task = taskService.updateTask(id, updatedTask);
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteTask(@PathVariable String id) {
    // taskService.deleteTask(id);
    // return ResponseEntity.noContent().build();

    taskService.deleteTask(id);
    return ResponseEntity.ok("Task with ID: " + id + " deleted successfully.");
  }

}
