package com.domain.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.models.Tasks;
import com.domain.repository.TaskRepository;

@Service
public class TaskService {
  @Autowired
  public TaskRepository repository;

  // Create
  public Tasks createTask(Tasks task) {
    task.setTasksId(UUID.randomUUID().toString().split("-")[0]);
    return repository.save(task);
  }

  // Read
  public List<Tasks> getAllTasks() {
    return repository.findAll();
  }

  // Sort by title
  public List<Tasks> sortByTitle() {
    return repository.findAll().stream()
        .sorted((task1, task2) -> task1.getTitle().compareToIgnoreCase(task2.getTitle()))
        .collect(Collectors.toList());
  }

  // Sort by priority
  public List<Tasks> sortByPriority() {
    return repository.findAll().stream()
        .sorted((task1, task2) -> task1.getPriority().compareToIgnoreCase(task2.getPriority()))
        .collect(Collectors.toList());
  }

  // Sort by deadline
  public List<Tasks> sortByDeadline() {
    return repository.findAll().stream()
        .sorted((task1, task2) -> task1.getDeadline().compareTo(task2.getDeadline()))
        .collect(Collectors.toList());
  }

  // Filter by deadline
  public List<Tasks> filterByDeadline(Date deadline) {
    return repository.findAll().stream()
        .filter(task -> isSameDay(task.getDeadline(), deadline))
        .collect(Collectors.toList());
  }

  private boolean isSameDay(Date date1, Date date2) {
    return date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate()
        .equals(date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate());
  }

  // Update task completion status
  public Tasks updateTaskCompletion(String id, boolean completed) {
    Tasks task = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    task.setCompleted(completed);
    return repository.save(task);
  }

  // Update task data
  public Tasks updateTask(String id, Tasks updatedTask) {
    Tasks existingTask = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    existingTask.setTitle(updatedTask.getTitle());
    existingTask.setPriority(updatedTask.getPriority());
    existingTask.setDeadline(updatedTask.getDeadline());
    existingTask.setCompleted(updatedTask.isCompleted());
    return repository.save(existingTask);
  }

  // Delete
  public void deleteTask(String id) {
    repository.deleteById(id);
  }
}
