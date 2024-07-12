package com.domain.repository;


import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.domain.models.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {
    List<Project> findByArchived(boolean archived);

    List<Project> findByTitleContaining(String title);

    @Query("{ 'tasks.title' : { $regex: ?0, $options: 'i' } }")
    List<Project> findByTasks_TitleContaining(String title);

    @Query("{ 'archived' : true }")
    List<Project> findArchivedProjects();

    @Query("{ 'archived' : false }")
    List<Project> findUnarchivedProjects();

    @SuppressWarnings("null")
    List<Project> findAll(Sort sort);
}
