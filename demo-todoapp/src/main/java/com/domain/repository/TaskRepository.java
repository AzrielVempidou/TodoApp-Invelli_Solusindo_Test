package com.domain.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.domain.models.Tasks;
@Repository
public interface TaskRepository  extends MongoRepository <Tasks, String>{
    List<Tasks> findByTitleContaining(String title);
    List<Tasks> findByPriority(String priority);
    List<Tasks> findByDeadline(Date deadline);
    List<Tasks> findByCompleted(boolean completed);
}
