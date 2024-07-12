package com.domain.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document; 


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
  
  
    @Id
    private String projectId;
    private String title;
    private String description;
    private boolean archived;
    @DBRef
    private List<Tasks> tasks;

    
}
