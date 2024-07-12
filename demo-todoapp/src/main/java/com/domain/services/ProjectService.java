package com.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.domain.models.Project;
import com.domain.repository.ProjectRepository;

@Service
public class ProjectService {
  @Autowired
  private ProjectRepository projectRepository;

  public Project createProject(Project project) {
    project.setProjectId(UUID.randomUUID().toString().split("-")[0]);
    return projectRepository.save(project);
  }

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }

  public Optional<Project> getProjectById(String id) {
    return projectRepository.findById(id);
  }

  public Project updateProject(Project project) {
    return projectRepository.save(project);
  }

  public void deleteProject(String id) {
    projectRepository.deleteById(id);
  }

  public List<Project> findByArchived(boolean archived) {
    return projectRepository.findByArchived(archived);
  }

  public List<Project> findByTitleContaining(String title) {
    return projectRepository.findByTitleContaining(title);
  }

  public void archiveProject(String id) {
    Optional<Project> optionalProject = projectRepository.findById(id);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.setArchived(true);
      projectRepository.save(project);
    }
  }

  public void unarchiveProject(String id) {
    Optional<Project> optionalProject = projectRepository.findById(id);
    if (optionalProject.isPresent()) {
      Project project = optionalProject.get();
      project.setArchived(false);
      projectRepository.save(project);
    }
  }

  public List<Project> findAllSortedBy(String field) {
    return projectRepository.findAll(Sort.by(Sort.Direction.ASC, field));
  }

}
