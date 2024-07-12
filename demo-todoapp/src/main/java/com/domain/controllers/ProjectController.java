package com.domain.controllers;

import com.domain.models.Project;
import com.domain.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Create a new project.
     *
     * @param project Project object to create
     * @return Created project object
     */
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    /**
     * Get all projects.
     *
     * @return List of all projects
     */
    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }

    /**
     * Get project by ID.
     *
     * @param id Project ID
     * @return Project object if found, else 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable String id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing project.
     *
     * @param project Updated project object
     * @return Updated project object
     */
    @PutMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project project) {
        Project updatedProject = projectService.updateProject(project);
        return ResponseEntity.ok(updatedProject);
    }

    /**
     * Delete a project by ID.
     *
     * @param id Project ID
     * @return 204 No Content if successful, else 404 Not Found
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable String id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Projects with ID: " + id + " deleted successfully.");

        // return ResponseEntity.noContent().build();
    }

    /**
     * Archive a project by ID.
     *
     * @param id Project ID
     * @return 200 OK if successful
     */
    @PutMapping("/archive/{id}")
    public ResponseEntity<String> archiveProject(@PathVariable String id) {
        projectService.archiveProject(id);
        return ResponseEntity.ok("Projects with ID: " + id + " archived successfully.");
    }

    /**
     * Unarchive a project by ID.
     *
     * @param id Project ID
     * @return 200 OK if successful
     */
    @PutMapping("/unarchive/{id}")
    public ResponseEntity<String> unarchiveProject(@PathVariable String id) {
        projectService.unarchiveProject(id);
        return ResponseEntity.ok("Projects with ID: " + id + " unarchive successfully.");
    }

    /**
     * Get all projects sorted by a field.
     *
     * @param field Field name to sort by
     * @return List of projects sorted by specified field
     */
    @GetMapping("/sortBy/{field}")
    public List<Project> getAllProjectsSortedBy(@PathVariable String field) {
        return projectService.findAllSortedBy(field);
    }
}
